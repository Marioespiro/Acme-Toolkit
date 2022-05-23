package acme.features.patron.dashboard;

import java.util.EnumMap;
import java.util.HashMap;
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
			request.unbind(entity, model, "totalNumberOfProposedPatronagesByStatus", "averageBudgetOfPatronagesStatusByCurrency", "deviationBudgetOfPatronagesStatusByCurrency", "maximumBudgetOfPatronagesStatusByCurrency", "minimumBudgetOfPatronagesStatusByCurrency");
		}

		@Override
		public PatronDashboard findOne(final Request<PatronDashboard> request) {
			assert request != null;
			final PatronDashboard result;
			final EnumMap<PatronageStatus, Integer> totalNumberOfPatronages = new EnumMap<>(PatronageStatus.class);
			final Map<Pair<String, PatronageStatus>, Double> averageBugdetPatronage = new HashMap<>();
			final Map<Pair<String, PatronageStatus>, Double> deviationBugdetPatronage = new HashMap<>();
			final Map<Pair<String, PatronageStatus>, Double> minimumBugdetPatronage = new HashMap<>();
			final Map<Pair<String, PatronageStatus>, Double> maximumBugdetPatronage = new HashMap<>();
			final Integer acceptedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.ACCEPTED);
			final Integer deniedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.DENIED);
			final Integer proposedPatronages = this.repository.totalNumberOfPatronages(PatronageStatus.PROPOSED);
			
			for(final Object[] obj: this.repository.deviationBugdetPatronage(PatronageStatus.ACCEPTED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				averageBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.ACCEPTED), res);
			}
			for(final Object[] obj: this.repository.deviationBugdetPatronage(PatronageStatus.PROPOSED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				averageBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.PROPOSED), res);
			}
			for(final Object[] obj: this.repository.deviationBugdetPatronage(PatronageStatus.DENIED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				averageBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.DENIED), res);
			}
			for(final Object[] obj: this.repository.averageBugdetPatronage(PatronageStatus.ACCEPTED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				deviationBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.ACCEPTED), res);
			}
			for(final Object[] obj: this.repository.averageBugdetPatronage(PatronageStatus.PROPOSED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				deviationBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.PROPOSED), res);
			}
			for(final Object[] obj: this.repository.averageBugdetPatronage(PatronageStatus.DENIED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				deviationBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.DENIED), res);
			}
			for(final Object[] obj: this.repository.maxBugdetPatronage(PatronageStatus.ACCEPTED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				maximumBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.ACCEPTED), res);
			}
			for(final Object[] obj: this.repository.maxBugdetPatronage(PatronageStatus.PROPOSED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				maximumBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.PROPOSED), res);
			}
			for(final Object[] obj: this.repository.maxBugdetPatronage(PatronageStatus.DENIED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				maximumBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.DENIED), res);
			}
			for(final Object[] obj: this.repository.minBugdetPatronage(PatronageStatus.ACCEPTED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				minimumBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.ACCEPTED), res);
			}
			for(final Object[] obj: this.repository.minBugdetPatronage(PatronageStatus.PROPOSED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				minimumBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.PROPOSED), res);
			}
			for(final Object[] obj: this.repository.minBugdetPatronage(PatronageStatus.DENIED)) {
				final Double res = Math.round(Double.valueOf(obj[1].toString()) * 100) / 100d;
				minimumBugdetPatronage.put(Pair.of(obj[0].toString(), PatronageStatus.DENIED), res);
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
