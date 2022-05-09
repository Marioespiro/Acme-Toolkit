package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Patron;

@Service
public class PatronPatronagePublishService implements AbstractUpdateService<Patron, Patronage> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronPatronageRepository repository;

		// AbstractUpdateService<Inventor, Item> interface ---------------------------


		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			final boolean result;
			final int patronageId;
			final Patronage patronage;
			final Patron patron;

			patronageId = request.getModel().getInteger("id");
			patronage = this.repository.findPatronageById(patronageId);
			patron = patronage.getPatron();
			result = !patronage.isPublished() && request.isPrincipal(patron);

			return result;
		}

		@Override
		public Patronage findOne(final Request<Patronage> request) {
			assert request != null;

			Patronage result;
			int id;

			id = request.getModel().getInteger("id");
			result = this.repository.findPatronageById(id);

			return result;
		}

		@Override
		public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link");
		}

		@Override
		public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			if (!errors.hasErrors("code")) {
				Patronage existing;

				existing = this.repository.findPatronageByCode(entity.getCode());
				errors.state(request, existing == null || existing.getId() == entity.getId(), "reference", "patron.patronage.form.error.duplicated");
			}

		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link", "inventor", "isPublished");
		}


		@Override
		public void update(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;

			entity.setPublished(true);
			this.repository.save(entity);
		}

}
