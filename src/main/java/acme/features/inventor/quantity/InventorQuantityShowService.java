package acme.features.inventor.quantity;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorQuantityShowService implements AbstractShowService<Inventor, Quantity>{

	// Internal state
	
	@Autowired
	protected InventorQuantityRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		Principal principal;
		principal = request.getPrincipal();
		Quantity quantity;
		int id;
		id = request.getModel().getInteger("id");
		quantity = this.repository.findQuantityById(id);
		final int activeId = principal.getActiveRoleId();
		return quantity.getItem().getInventor().getId()==activeId;
	}
	
	@Override
	public Quantity findOne(final Request<Quantity> request) {
		assert request != null;
		
		Integer id;
		Quantity result;
		id = request.getModel().getInteger("id");
		result = this.repository.findQuantityById(id);
		return result;
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final Item item = this.repository.findItemsByQuantity(entity.getId());

		request.unbind(entity, model, "amount");
		model.setAttribute("itemCode", item.getCode());
		model.setAttribute("itemName", item.getName());
		model.setAttribute("itemRetailPrice", item.getRetailPrice());
		model.setAttribute("itemDescription", item.getDescription());
		model.setAttribute("itemArtifactType", item.getItemType());
		model.setAttribute("itemTechnology", item.getTechnology());
		model.setAttribute("itemLink", item.getLink());
		model.setAttribute("isPublished", entity.getToolkit().isPublished());
		
	}
	
	
}