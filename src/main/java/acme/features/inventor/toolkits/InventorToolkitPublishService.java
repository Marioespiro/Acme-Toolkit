package acme.features.inventor.toolkits;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorToolkitPublishService implements AbstractUpdateService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractUpdateService<Inventor, Item> interface ---------------------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		final boolean result;
		final int id;
		final Toolkit toolkit;
		final Inventor inventor;
		
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(id);
		Principal principal;
		principal = request.getPrincipal();
		final List<Integer> toolkits = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
		return !toolkit.isPublished() && toolkits.contains(id);
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

		request.bind(entity, errors, "code", "description", "link", "title", "assemblyNotes");
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

	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "description", "link", "title", "assemblyNotes", "isPublished");
	}


	@Override
	public void update(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;

		entity.setPublished(true);
		final Collection<Quantity> quantities =this.repository.findAllQuantitiesByToolkitId(entity.getId());
		for(final Quantity q: quantities) {
			q.getItem().setPublished(true);
			this.repository.save(q);
		}
		this.repository.save(entity);
	}

}
