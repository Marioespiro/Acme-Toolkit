package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage_reports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Patron;

@Service
public class PatronPatronageDeleteService implements AbstractDeleteService<Patron, Patronage> {
	
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronPatronageRepository repository;

		// AbstractDeleteService<Inventor, Item> interface -------------------------


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
		public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			request.bind(entity, errors, "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link");
		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			model.setAttribute("inventors", this.repository.findInventors());
			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startTime", "creationTime", "endingTime", "link", "inventor", "isPublished", "inventors");
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
		public void validate(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
		}

		@Override
		public void delete(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;

			if(!entity.isPublished()) {
				final Collection<PatronageReport> patronageReports;

				patronageReports = this.repository.findAllPatronageReportsByPatronageId(entity.getId());
				for (final PatronageReport pr : patronageReports) {
					this.repository.deletePatronageReportById(pr.getId());
				}
				this.repository.delete(entity);
			}
		}

}
