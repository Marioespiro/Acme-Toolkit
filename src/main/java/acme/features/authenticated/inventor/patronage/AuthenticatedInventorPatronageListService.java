package acme.features.authenticated.inventor.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;


@Service
public class AuthenticatedInventorPatronageListService implements AbstractListService<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedInventorPatronageRepository repository;

	// AbstractListService<Employer, Duty> interface ---------------------------
	
	@Override
	public boolean authorise(final Request<Patronage> request) {
		assert request != null;

		return true;
	}

	@Override
	public Collection<Patronage> findMany(final Request<Patronage> request) {
		assert request != null;

		Collection<Patronage> result;
//		int id;
//		id = request.getModel().getInteger("id");
//		result = this.repository.findAllPatronagesByInventorId(id);
		Principal principal;

		principal = request.getPrincipal();
		result = this.repository.findAllPatronagesByInventorId(principal.getActiveRoleId());
//		Principal principal;
//
//		principal = request.getPrincipal();
//		result = this.repository.findManyByEmployerId(principal.getActiveRoleId());
		return result;
	}

	@Override
	public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "status", "code", "legalStuff", "budget", "periodOfTime", "creationTime", "endingTime", "retailPrice", "link");
		
	}

}
