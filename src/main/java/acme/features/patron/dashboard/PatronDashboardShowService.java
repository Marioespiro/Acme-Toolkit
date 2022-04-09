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
			final Integer totalNumberAcceptedPatronage = entity.getTotalNumberOfProposedPatronagesByStatus().get(PatronageStatus.ACCEPTED);
			model.setAttribute("totalNumberOfAcceptedPatronage", totalNumberAcceptedPatronage);
			final Integer totalNumberDeniedPatronage = entity.getTotalNumberOfProposedPatronagesByStatus().get(PatronageStatus.DENIED);
			model.setAttribute("totalNumberOfDeniedPatronage", totalNumberDeniedPatronage);
			final Integer totalNumberProposedPatronage = entity.getTotalNumberOfProposedPatronagesByStatus().get(PatronageStatus.PROPOSED);
			model.setAttribute("totalNumberOfProposedPatronage", totalNumberProposedPatronage);
			this.formatStrings(deniedAvg, acceptedAvg, proposedAvg, entity.getAverageBudgetOfPatronagesStatusByCurrency());
			model.setAttribute("averageDeniedPatronages", deniedAvg);
			model.setAttribute("averageProposedPatronages", proposedAvg);
			model.setAttribute("averageAcceptedPatronages", acceptedAvg);
			
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
			final Map<Pair<String, PatronageStatus>, Double> avg = new HashMap<Pair<String,PatronageStatus>, Double>();
				for(final String currency: allCurrencies) {
					final Double accepted =this.repository.averageBugdetProposedPatronage(PatronageStatus.ACCEPTED, currency);
					final Double denied =this.repository.averageBugdetProposedPatronage(PatronageStatus.DENIED, currency);
					final Double proposed =this.repository.averageBugdetProposedPatronage(PatronageStatus.PROPOSED, currency);
					avg.put(Pair.of(currency, PatronageStatus.PROPOSED), proposed);
					avg.put(Pair.of(currency, PatronageStatus.DENIED), denied);
					avg.put(Pair.of(currency, PatronageStatus.ACCEPTED), accepted);
				}
			totalNumberOfPatronages.put(PatronageStatus.ACCEPTED, acceptedPatronages);
			totalNumberOfPatronages.put(PatronageStatus.DENIED, deniedPatronages);
			totalNumberOfPatronages.put(PatronageStatus.PROPOSED, proposedPatronages);
			
			result = new PatronDashboard();
			result.setTotalNumberOfProposedPatronagesByStatus(totalNumberOfPatronages);
			result.setAverageBudgetOfPatronagesStatusByCurrency(avg);
			return result;
		}

}
