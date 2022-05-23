package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;
import acme.util.SpamFilterService;

@Service
public class InventorToolkitUpdateService implements AbstractUpdateService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractUpdateService<Inventor, Item> interface ---------------------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		final int inventorId;
		final Toolkit toolkit;
		
		inventorId = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(inventorId);
		Principal principal;
		principal = request.getPrincipal();
		final Collection<Toolkit> toolkits = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId());
		return !toolkit.isPublished() && toolkits.contains(toolkit);
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);

		return result;
	}
	
	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "title", "code", "assemblyNotes", "description", "link");
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		if (!errors.hasErrors("code")) {
			Toolkit existing;

			existing = this.repository.findToolkitByCode(entity.getCode());
			errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "authenticated.inventor.toolkit.form.error.duplicated");
		}
		
		if(this.spamFilterService.isSpam(entity.getDescription())) {
			errors.state(request, false, "description", "authenticated.inventor.toolkit.form.error.spam");
		}
		
		if(this.spamFilterService.isSpam(entity.getTitle())) {
			errors.state(request, false, "title", "authenticated.inventor.toolkit.form.error.spam");
		}
		
		if(this.spamFilterService.isSpam(entity.getLink())) {
			errors.state(request, false, "link", "authenticated.inventor.toolkit.form.error.spam");
		}
		
		if(this.spamFilterService.isSpam(entity.getAssemblyNotes())) {
			errors.state(request, false, "assemblyNotes", "authenticated.inventor.toolkit.form.error.spam");
		}

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "title", "code", "assemblyNotes", "description", "link", "isPublished");
	}


	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

}