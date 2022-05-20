package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.items.ItemType;
import acme.entities.quantities.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;

@Service
public class InventorQuantityCreateService implements AbstractCreateService<Inventor, Quantity> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorQuantityRepository repository;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		final int inventorid = request.getPrincipal().getActiveRoleId();
		final Toolkit toolkit =  this.repository.findToolkitById(request.getModel().getInteger("toolkitId"));
		return toolkit.getInventor().getId() == inventorid && !toolkit.isPublished();
	}

	@Override
	public void bind(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		entity.setItem(this.repository.findItemByName(request.getModel().getAttribute("itemName").toString()));
		request.bind(entity, errors, "amount", "itemName");
	}

	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "amount", "itemName");
		final Collection<Item> items = this.repository.findItemsByInventor(request.getPrincipal().getActiveRoleId());
		model.setAttribute("items", items);
		model.setAttribute("toolkitId", request.getModel().getAttribute("toolkitId"));
		final Toolkit toolkit =  this.repository.findToolkitById(request.getModel().getInteger("toolkitId"));
		model.setAttribute("isPublished", toolkit.isPublished());

	}

	@Override
	public Quantity instantiate(final Request<Quantity> request) {
		assert request != null;
		int toolkitId;
		Toolkit toolkit;
		final Quantity result = new Quantity();
		toolkitId = request.getModel().getInteger("toolkitId");
		toolkit = this.repository.findToolkitById(toolkitId);
		result.setToolkit(toolkit);
		return result;
	}

	@Override
	public void validate(final Request<Quantity> request, final Quantity entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(entity.getItem().getItemType() == ItemType.TOOL) {
			errors.state(request, entity.getAmount()<=1, "amount", "authenticated.inventor.quantity.form.error.amount");
		}
		if(this.repository.findQuantityByToolkit(entity.getToolkit().getId()).stream().anyMatch(x->x.getItem() == entity.getItem())) {
			errors.state(request, entity.getAmount()<=1, "itemName", "authenticated.inventor.quantity.form.error.duplicated.item");
		}
		

	}

	@Override
	public void create(final Request<Quantity> request, final Quantity entity) {
		assert request != null;
		assert entity != null;
		final Item item;
		final String name;
		
		name = (String) request.getModel().getAttribute("itemName");
		item = this.repository.findItemByName(name);

		entity.setItem(item);
		this.repository.save(entity);

	}




}