package acme.features.patron.patronage;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronages.Patronage;
import acme.entities.patronages.PatronageStatus;
import acme.entities.system_configurations.SystemConfiguration;
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
			
			final List<SystemConfiguration> configurationColl = new ArrayList<>(this.repository.findAllConfigurations());
			final String acceptedCurrencies = configurationColl.get(0).getAcceptedCurrencies();
			final List<String> currencies = new ArrayList<String>();
			for(final String s : acceptedCurrencies.split(";")) {
				currencies.add(s);
			}
			
			if (!errors.hasErrors("code")) {
				Patronage existing;

				existing = this.repository.findPatronageByCode(entity.getCode());
				errors.state(request, existing == null, "code", "patron.patronage.form.error.duplicated");
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
		public void bind(final Request<Patronage> request, final Patronage entity, final Errors errors) {
			assert request != null;
			assert entity != null;
			assert errors != null;
			
			final Calendar calCreation = Calendar.getInstance();
			calCreation.add(Calendar.MILLISECOND, -100);
			entity.setCreationTime(calCreation.getTime());
			
			entity.setInventor(this.repository.findInventorById(request.getModel().getInteger("inventorId")));
			

			request.bind(entity, errors, "code", "legalStuff", "budget", "startTime", "endingTime", "link", "inventorId");

		}

		@Override
		public void unbind(final Request<Patronage> request, final Patronage entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;
			
			
			model.setAttribute("inventors", this.repository.findInventors());
			request.unbind(entity, model, "status", "code", "legalStuff", "budget", "startTime", "endingTime", "link", "inventor", "isPublished");
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
			result.setStatus(PatronageStatus.PROPOSED);
			
		
			return result;
		}

		@Override
		public void create(final Request<Patronage> request, final Patronage entity) {
			assert request != null;
			assert entity != null;

			this.repository.save(entity);
		}

}
