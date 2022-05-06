package acme.features.inventor.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.util.SpamFilterService;

@Service
public class InventorItemUpdateService implements AbstractUpdateService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

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

		request.bind(entity, errors, "name", "code", "technology", "description", "retailPrice", "link");
	}

	@Override
	public void validate(final Request<Item> request, final Item entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if (!errors.hasErrors("code")) {
			Item existing;

			existing = this.repository.findItemByCode(entity.getCode());
			errors.state(request, existing == null, "code", "inventor.item.form.error.duplicated");
		}
		
		if(this.spamFilterService.isSpam(entity.getDescription())) {
			errors.state(request, false, "description", "inventor.item.form.error.spam");
		}
		
		if(this.spamFilterService.isSpam(entity.getName())) {
			errors.state(request, false, "name", "inventor.item.form.error.spam");
		}

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "technology", "description", "retailPrice", "link", "itemType", "isPublished");
	}


	@Override
	public void update(final Request<Item> request, final Item entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}
