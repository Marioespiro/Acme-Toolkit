package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.system_configurations.SystemConfiguration;
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
			
			final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
			final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
			final List<String> currencies = new ArrayList<String>();
			for(final String s : acceptedCurrencies.split(";")) {
				currencies.add(s);
			}
			
			if (!errors.hasErrors("code")) {
				Patronage existing;

				existing = this.repository.findPatronageByCode(entity.getCode());
				errors.state(request, existing == null || existing.getId() == entity.getId(), "code", "patron.patronage.form.error.duplicated");
			}
			
			if(!errors.hasErrors("budget")) {
				errors.state(request, !(!currencies.contains(entity.getBudget().getCurrency()) || entity.getBudget().getCurrency() == null ||
					entity.getBudget().getCurrency().length() == 0),
					"budget", "patron.patronage.form.error.incorrectCurrency");
				errors.state(request, !(entity.getBudget().getAmount() <= 0.0 || entity.getBudget().getAmount() == null),
					"budget", "patron.patronage.form.error.incorrectQuantity");
			}
			
			if(!errors.hasErrors("startTime")) {
				final Date creationTime = entity.getCreationTime();
				final Calendar calCreation = Calendar.getInstance();
				calCreation.setTime(creationTime);
				calCreation.add(Calendar.MONTH, 1);
				final Date creationDateAfterMonth = calCreation.getTime();
				final Date startTime = entity.getStartTime();
				
				errors.state(request, (startTime.after(creationDateAfterMonth)),
					"startTime", "patron.patronage.form.error.creationDateAfterMonth");
			}
			
			if(!errors.hasErrors("endTime")) {
				if(entity.getStartTime() != null) {
					final Date startTime = entity.getStartTime();
					final Calendar calStart = Calendar.getInstance();
					calStart.setTime(startTime);
					calStart.add(Calendar.MONTH, 1);
					
					final Date startDateAfterMonth = calStart.getTime();
					final Date endingTime = entity.getEndingTime();
					
					errors.state(request, (endingTime.after(startDateAfterMonth)),
						"endingTime", "patron.patronage.form.error.startDateAfterMonth");
				}		
			}

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
		public void update(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;

			entity.setPublished(true);
			this.repository.save(entity);
		}

}
