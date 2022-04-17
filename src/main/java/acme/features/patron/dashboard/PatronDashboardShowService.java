package acme.features.patron.dashboard;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.patronages.PatronageStatus;
import acme.forms.PatronDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Patron;

@Service
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
			request.unbind(entity, model, "averageBugdetPatronage");
			final List<String> deniedAvg = new ArrayList<String>();
			final List<String> acceptedAvg = new ArrayList<String>();
			final List<String> proposedAvg = new ArrayList<String>();
			final List<String> deniedDvt = new ArrayList<String>();
			final List<String> acceptedDvt = new ArrayList<String>();
			final List<String> proposedDvt = new ArrayList<String>();
			final List<String> maxDenied = new ArrayList<String>();
			final List<String> maxAccepted = new ArrayList<String>();
			final List<String> maxProposed = new ArrayList<String>();
			final List<String> minDenied = new ArrayList<String>();
			final List<String> minAccepted = new ArrayList<String>();
			final List<String> minProposed = new ArrayList<String>();
			final Integer totalNumberAcceptedPatronage = entity.getTotalNumberOfProposedPatronagesByStatus().get(PatronageStatus.ACCEPTED);
			model.setAttribute("totalNumberOfAcceptedPatronage", totalNumberAcceptedPatronage);
			final Integer totalNumberDeniedPatronage = entity.getTotalNumberOfProposedPatronagesByStatus().get(PatronageStatus.DENIED);
			model.setAttribute("totalNumberOfDeniedPatronage", totalNumberDeniedPatronage);
			final Integer totalNumberProposedPatronage = entity.getTotalNumberOfProposedPatronagesByStatus().get(PatronageStatus.PROPOSED);
			model.setAttribute("totalNumberOfProposedPatronage", totalNumberProposedPatronage);
			this.formatStrings(deniedAvg, acceptedAvg, proposedAvg, entity.getAverageBudgetOfPatronagesStatusByCurrency());
			this.formatStrings(minDenied, minAccepted, minProposed, entity.getMinimumBudgetOfPatronagesStatusByCurrency());
			this.formatStrings(maxDenied, maxAccepted, maxProposed, entity.getMaximumBudgetOfPatronagesStatusByCurrency());
			this.formatStrings(deniedDvt, acceptedDvt, proposedDvt, entity.getDeviationBudgetOfPatronagesStatusByCurrency());
			model.setAttribute("averageDeniedPatronages", deniedAvg);
			model.setAttribute("averageProposedPatronages", proposedAvg);
			model.setAttribute("averageAcceptedPatronages", acceptedAvg);
			model.setAttribute("deviationDeniedPatronages", deniedDvt);
			model.setAttribute("deviationProposedPatronages", acceptedDvt);
			model.setAttribute("deviationAcceptedPatronages", proposedDvt);
			model.setAttribute("maxDeniedPatronages", maxDenied);
			model.setAttribute("maxProposedPatronages", maxProposed);
			model.setAttribute("maxAcceptedPatronages", maxAccepted);
			model.setAttribute("minDeniedPatronages", minDenied);
			model.setAttribute("minProposedPatronages", minProposed);
			model.setAttribute("minAcceptedPatronages", minAccepted);
			
		}
		private void formatStrings(final List<String> deniedList, final List<String> acceptedList, final List<String> proposedList, final Map<Pair<String, PatronageStatus>, Double> map) {
			for(final Pair<String, PatronageStatus> key: map.keySet()) {
				final String currency = key.getFirst();
				final PatronageStatus status = key.getSecond();
				final Double avg = map.get(key);
				if(status.equals(PatronageStatus.DENIED)) {
					deniedList.add(currency+ " -> "+avg.toString());
				}else if(status.equals(PatronageStatus.ACCEPTED)) {
					acceptedList.add(currency+ " -> "+avg.toString());
				}else {
					proposedList.add(currency+ " -> "+avg.toString());
				}
			};
		};
		
		

		@Override
		public PatronDashboard findOne(final Request<PatronDashboard> request) {
			assert request != null;
			final PatronDashboard result;
			final Map<PatronageStatus, Integer> totalNumberOfPatronages = new HashMap<PatronageStatus, Integer>();
			final Map<Pair<String, PatronageStatus>, Double> averageBugdetPatronage = new HashMap<Pair<String, PatronageStatus >, Double>();
			final Map<Pair<String, PatronageStatus>, Double> deviationBugdetPatronage = new HashMap<Pair<String, PatronageStatus>, Double>();
			final Map<Pair<String, PatronageStatus>, Double> minimumBugdetPatronage = new HashMap<Pair<String, PatronageStatus>, Double>();
			final Map<Pair<String, PatronageStatus>, Double> maximumBugdetPatronage = new HashMap<Pair<String, PatronageStatus>, Double>();
//			final List<Patronage> totalPatronage = this.repository.all;
			final Integer acceptedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.ACCEPTED);
			final Integer deniedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.DENIED);
			final Integer proposedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.PROPOSED);
			final Collection<String> allCurrencies = this.repository.currencies();
			for(final String currency: allCurrencies) {
				final Double acceptedDvt =this.repository.deviationBugdetPatronage(PatronageStatus.ACCEPTED, currency);
				final Double deniedDvt =this.repository.deviationBugdetPatronage(PatronageStatus.DENIED, currency);
				final Double proposedDvt =this.repository.deviationBugdetPatronage(PatronageStatus.PROPOSED, currency);
				final Double acceptedAvg =this.repository.averageBugdetPatronage(PatronageStatus.ACCEPTED, currency);
				final Double deniedAvg =this.repository.averageBugdetPatronage(PatronageStatus.DENIED, currency);
				final Double proposedAvg =this.repository.averageBugdetPatronage(PatronageStatus.PROPOSED, currency);
				final Double maxAccepted =this.repository.maxBugdetPatronage(PatronageStatus.ACCEPTED, currency);
				final Double maxDenied =this.repository.maxBugdetPatronage(PatronageStatus.DENIED, currency);
				final Double maxProposed =this.repository.maxBugdetPatronage(PatronageStatus.PROPOSED, currency);
				final Double minAccepted =this.repository.maxBugdetPatronage(PatronageStatus.ACCEPTED, currency);
				final Double minDenied =this.repository.maxBugdetPatronage(PatronageStatus.DENIED, currency);
				final Double minProposed =this.repository.maxBugdetPatronage(PatronageStatus.PROPOSED, currency);
				averageBugdetPatronage.put(Pair.of(currency, PatronageStatus.PROPOSED), proposedAvg);
				averageBugdetPatronage.put(Pair.of(currency, PatronageStatus.DENIED), deniedAvg);
				averageBugdetPatronage.put(Pair.of(currency, PatronageStatus.ACCEPTED), acceptedAvg);
				deviationBugdetPatronage.put(Pair.of(currency, PatronageStatus.PROPOSED), proposedDvt);
				deviationBugdetPatronage.put(Pair.of(currency, PatronageStatus.DENIED), deniedDvt);
				deviationBugdetPatronage.put(Pair.of(currency, PatronageStatus.ACCEPTED), acceptedDvt);
				maximumBugdetPatronage.put(Pair.of(currency, PatronageStatus.PROPOSED), maxProposed);
				maximumBugdetPatronage.put(Pair.of(currency, PatronageStatus.DENIED), maxDenied);
				maximumBugdetPatronage.put(Pair.of(currency, PatronageStatus.ACCEPTED), maxAccepted);
				minimumBugdetPatronage.put(Pair.of(currency, PatronageStatus.PROPOSED), minProposed);
				minimumBugdetPatronage.put(Pair.of(currency, PatronageStatus.DENIED), minDenied);
				minimumBugdetPatronage.put(Pair.of(currency, PatronageStatus.ACCEPTED), minAccepted);
		}
			
			totalNumberOfPatronages.put(PatronageStatus.ACCEPTED, acceptedPatronages);
			totalNumberOfPatronages.put(PatronageStatus.DENIED, deniedPatronages);
			totalNumberOfPatronages.put(PatronageStatus.PROPOSED, proposedPatronages);
			
			result = new PatronDashboard();
			result.setTotalNumberOfProposedPatronagesByStatus(totalNumberOfPatronages);
			result.setAverageBudgetOfPatronagesStatusByCurrency(averageBugdetPatronage);
			result.setDeviationBudgetOfPatronagesStatusByCurrency(deviationBugdetPatronage);
			result.setMaximumBudgetOfPatronagesStatusByCurrency(maximumBugdetPatronage);
			result.setMinimumBudgetOfPatronagesStatusByCurrency(minimumBugdetPatronage);
			return result;
		}

}
