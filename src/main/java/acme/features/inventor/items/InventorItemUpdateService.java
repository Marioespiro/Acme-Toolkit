package acme.features.inventor.items;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.system_configurations.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.util.SpamFilterService;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {

	// Internal state ---------------------------------------------------------
	
	private final String DESCRIPTION = "description";
	private final String RETAIL_PRICE = "retailPrice";

	@Autowired
	protected InventorItemRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractUpdateService<Inventor, Item> interface ---------------------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		final boolean result;
		final int itemId;
		final Item item;
		final Inventor inventor;
		
		itemId = request.getModel().getInteger("id");
		item = this.repository.findItemById(itemId);
		inventor = item.getInventor();
		result = !item.isPublished() && request.isPrincipal(inventor);

		return result;
	}
	
	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}
	
	@Override
	public void bind(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "name", "code", "technology", this.DESCRIPTION, this.RETAIL_PRICE, "link");
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
		final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
		final List<String> currencies = Arrays.asList(acceptedCurrencies.split(";"));

		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "inventor.item.form.error.duplicated");
		}
		
		if(!errors.hasErrors(this.RETAIL_PRICE)) {
			errors.state(request, !(!currencies.contains(entity.getRetailPrice().getCurrency()) || entity.getRetailPrice().getCurrency() == null ||
				entity.getRetailPrice().getCurrency().length() == 0),
				this.RETAIL_PRICE, "inventor.item.form.error.incorrectCurrency");
			errors.state(request, !(entity.getRetailPrice().getAmount() <= 0.0 || entity.getRetailPrice().getAmount() == null),
				this.RETAIL_PRICE, "inventor.item.form.error.incorrectQuantity");
		}
		
		if(!errors.hasErrors(this.DESCRIPTION)) {
			errors.state(request, !this.spamFilterService.isSpam(entity.getDescription()), this.DESCRIPTION, "inventor.item.form.error.spam");
		}
		
		if(!errors.hasErrors("name")) {
			errors.state(request, !this.spamFilterService.isSpam(entity.getName()), "name", "inventor.item.form.error.spam");
		}

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", this.DESCRIPTION, this.RETAIL_PRICE, "link", "itemType", "isPublished");
	}


	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
