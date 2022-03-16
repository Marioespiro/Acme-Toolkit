package acme.features.patron.dashboard;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.patronages.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

public class PatronDashboardShowService implements AbstractShowService<Patron, PatronDashboard>{
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronDashboardRepository repository;

		@Override
		public boolean authorise(final Request<PatronDashboard> request) {
			assert request != null;

			return true;
		}

		@Override
		public void unbind(final Request<PatronDashboard> request, final PatronDashboard entity, final Model model) {
			assert request != null;
			assert entity != null;
			assert model != null;

			request.unbind(entity, model, "totalNumberOfProposedPatronages","totalNumberOfAcceptedPatronages", "totalNumberOfDeniedPatronages", "averageBugdetProposedPatronage","deviationBugdetProposedPatronage","minimumBugdetProposedPatronage","maximumBugdetProposedPatronage","averageBugdetAcceptedPatronage","deviationBugdetAcceptedPatronage","minimumBugdetAcceptedPatronage", "maximumBugdetAcceptedPatronage", "averageBugdetDeniedPatronage", "deviationBugdetDeniedPatronage", "minimumBugdetDeniedPatronage", "maximumBugdetDeniedPatronage");
		}

		@Override
		public PatronDashboard findOne(final Request<PatronDashboard> request) {
			assert request != null;

			PatronDashboard result;
			final Integer 						totalNumberOfProposedPatronages;
			final Integer						totalNumberOfAcceptedPatronages;
			final Integer						totalNumberOfDeniedPatronages;
			final Double						averageBugdetProposedPatronage;
			final Double						deviationBugdetProposedPatronage;
			final Double						minimumBugdetProposedPatronage;
			final Double						maximumBugdetProposedPatronage;
			final Double						averageBugdetAcceptedPatronage;
			final Double						deviationBugdetAcceptedPatronage;
			final Double						minimumBugdetAcceptedPatronage;
			final Double						maximumBugdetAcceptedPatronage;
			final Double						averageBugdetDeniedPatronage;
			final Double						deviationBugdetDeniedPatronage;
			final Double						minimumBugdetDeniedPatronage;
			final Double						maximumBugdetDeniedPatronage;

//			final List<Patronage> totalPatronage = this.repository.all;
			totalNumberOfProposedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.PROPOSED);
			totalNumberOfAcceptedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.ACCEPTED);
			totalNumberOfDeniedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.DENIED);
			averageBugdetProposedPatronage = this.repository.averageBugdetProposedPatronage(PatronageStatus.PROPOSED);
			deviationBugdetProposedPatronage = this.repository.deviationBugdetPatronage(PatronageStatus.PROPOSED);
			minimumBugdetProposedPatronage = this.repository.minimumBugdetPatronage(PatronageStatus.PROPOSED);
			maximumBugdetProposedPatronage = this.repository.maximumBugdetPatronage(PatronageStatus.PROPOSED);
			averageBugdetAcceptedPatronage = this.repository.averageBugdetProposedPatronage(PatronageStatus.ACCEPTED);
			deviationBugdetAcceptedPatronage = this.repository.deviationBugdetPatronage(PatronageStatus.ACCEPTED);
			minimumBugdetAcceptedPatronage = this.repository.minimumBugdetPatronage(PatronageStatus.ACCEPTED);
			maximumBugdetAcceptedPatronage = this.repository.maximumBugdetPatronage(PatronageStatus.ACCEPTED);
			averageBugdetDeniedPatronage = this.repository.averageBugdetProposedPatronage(PatronageStatus.DENIED);
			deviationBugdetDeniedPatronage = this.repository.deviationBugdetPatronage(PatronageStatus.DENIED);
			minimumBugdetDeniedPatronage = this.repository.minimumBugdetPatronage(PatronageStatus.DENIED);
			maximumBugdetDeniedPatronage = this.repository.maximumBugdetPatronage(PatronageStatus.DENIED);
			
			result = new PatronDashboard();
			result.setTotalNumberOfAcceptedPatronages(totalNumberOfAcceptedPatronages);
			result.setTotalNumberOfProposedPatronages(totalNumberOfProposedPatronages);
			result.setTotalNumberOfDeniedPatronages(totalNumberOfDeniedPatronages);
			result.setAverageBugdetAcceptedPatronage(averageBugdetAcceptedPatronage);
			result.setAverageBugdetProposedPatronage(averageBugdetProposedPatronage);
			result.setAverageBugdetDeniedPatronage(averageBugdetDeniedPatronage);
			result.setDeviationBugdetAcceptedPatronage(deviationBugdetAcceptedPatronage);
			result.setDeviationBugdetProposedPatronage(deviationBugdetProposedPatronage);
			result.setDeviationBugdetDeniedPatronage(deviationBugdetDeniedPatronage);
			result.setMinimumBugdetAcceptedPatronage(minimumBugdetAcceptedPatronage);
			result.setMinimumBugdetProposedPatronage(minimumBugdetProposedPatronage);
			result.setMinimumBugdetDeniedPatronage(minimumBugdetDeniedPatronage);
			result.setMaximumBugdetAcceptedPatronage(maximumBugdetAcceptedPatronage);
			result.setMaximumBugdetProposedPatronage(maximumBugdetProposedPatronage);
			result.setMaximumBugdetDeniedPatronage(maximumBugdetDeniedPatronage);
			return result;
		}

}
