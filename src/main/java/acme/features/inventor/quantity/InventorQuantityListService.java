package acme.features.inventor.quantity;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
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
		final Collection<Quantity> quantities;
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		final int activeId = principal.getActiveRoleId();
		quantities = this.repository.findQuantityByToolkit(toolkitId);
		final Collection<Quantity> toolkits = this.repository.findAllQuantitiesByInventorId(principal.getActiveRoleId());
		boolean result = true;
		for(final Quantity q: quantities) {
			if(q.getItem().getInventor().getId()!=activeId) {
				result = false;
		}
			}
		return result;
	}
	
	@Override
	public Collection<Quantity> findMany(final Request<Quantity> request) {
		assert request != null;
		
		final Integer toolkitId = request.getModel().getInteger("toolkitId");
		return this.repository.findQuantityByToolkit(toolkitId);
	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Collection<Quantity> entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		int toolkitId;
		toolkitId = request.getModel().getInteger("toolkitId");
		model.setAttribute("toolkitId", toolkitId);

	}
	
	@Override
	public void unbind(final Request<Quantity> request, final Quantity entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		final String name = entity.getItem().getName();

		model.setAttribute("itemName", name);

		request.unbind(entity, model, "amount");
	}
	
	
	
	
	
	
}