package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolkitListService implements AbstractListService<Inventor, Toolkit>{

	// Internal state
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Collection<Toolkit> findMany(final Request<Toolkit> request) {
		assert request != null;
		
		Collection<Toolkit> result;
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId());

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
	}
	
	
	
	
	
	
}


