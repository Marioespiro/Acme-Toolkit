package acme.features.inventor.quantity;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorQuantityListService implements AbstractListService<Inventor, Quantity>{

	// Internal state
	
	@Autowired
	protected InventorQuantityRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Quantity> request) {
		assert request != null;
		Principal principal;
		principal = request.getPrincipal();
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		final int activeId = principal.getActiveRoleId();
		final Toolkit toolkit = this.repository.findToolkitById(toolkitId);
		return toolkit.getInventor().getId() == activeId;
	}
	
	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		return this.repository.findQuantityByToolkit(toolkitId);
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String name = entity.getItem().getName();

		model.setAttribute("itemName", name);

		request.unbind(entity, model, "amount");
		model.setAttribute("itemType", entity.getItem().getItemType().toString().toLowerCase());
		model.setAttribute("itemDescription", entity.getItem().getDescription());
	}
	
	
	
	
	
	
}