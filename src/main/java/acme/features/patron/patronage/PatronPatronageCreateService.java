package acme.features.patron.patronage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Patron;

@Service
public class PatronPatronageCreateService implements AbstractCreateService<Patron, Patronage> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronPatronageRepository repository;

		// AbstractCreateService<Employer, Job> interface -------------------------


		@Override
		public boolean authorise(final Request<Patronage> request) {
			assert request != null;

			return true;
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
		public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;

			request.bind(entity, errors, "code");
			request.bind(entity, errors, "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link");

		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link", "inventor", "isPublished");
		}

		@Override
		public Patronage instantiate(final Request<Patronage> request) {
			assert request != null;

			Patronage result;
			final Patron patron;

			patron = this.repository.findOnePatronById(request.getPrincipal().getActiveRoleId());
			result = new Patronage();
			result.setPublished(false);
			result.setPatron(patron);
			result.setStatus(PatronageStatus.ACCEPTED);

			return result;
		}

		@Override
		public void create(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

}
