/*
 * AuthenticatedConsumerCreateService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.administrator.dashboard;

import java.util.EnumMap;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import acme.entities.items.ItemType;
import acme.entities.patronages.PatronageStatus;
import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractShowService;

@Service
public class AdministratorDashboardShowService implements AbstractShowService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "totalNumberOfComponents", "averageRetailPriceOfComponentsByTechnologyAndCurrency", "deviationRetailPriceOfComponentsByTechnologyAndCurrency",
			"minimumRetailPriceOfComponentsByTechnologyAndCurrency", "maximumRetailPriceOfComponentsByTechnologyAndCurrency", "totalNumberOfTools",
			"averageRetailPriceOfToolsByCurrency", "deviationRetailPriceOfToolsByCurrency", "minimumRetailPriceOfToolsByCurrency", "maximumRetailPriceOfToolsByCurrency",
			"totalNumberOfPatronagesByStatus", "averagePatronagesBudgetByStats", "deviationPatronagesBudgetByStats", "minimumPatronagesBudgetByStats", "maximumPatronagesBudgetByStats");
	}
	/*
	 * 
	EnumMap<PatronageStatus, Double>		maximumPatronagesBudgetByStats;
	 */

	@Override
	public AdministratorDashboard findOne(final Request<AdministratorDashboard> request) {
		assert request != null;

		final AdministratorDashboard result = new AdministratorDashboard();
		final EnumMap<PatronageStatus, Integer> mapTotalNumberPI = new EnumMap<>(PatronageStatus.class);
		final EnumMap<PatronageStatus, Double> mapAveragePD = new EnumMap<>(PatronageStatus.class);
		final EnumMap<PatronageStatus, Double> mapDeviationPD = new EnumMap<>(PatronageStatus.class);
		final EnumMap<PatronageStatus, Double> mapMinumumPD = new EnumMap<>(PatronageStatus.class);
		final EnumMap<PatronageStatus, Double> mapMaximumPD = new EnumMap<>(PatronageStatus.class);
		final Map<String, Double> avrRtBC = new HashedMap();
		final Map<String, Double> dvaRtBC = new HashedMap();
		final Map<String, Double> minRtBC = new HashedMap();
		final Map<String, Double> maxRtBC = new HashedMap();
		final Map<Pair<String, String>, Double> avrRtBCT = new HashedMap();
		final Map<Pair<String, String>, Double> dvaRtBCT = new HashedMap();
		final Map<Pair<String, String>, Double> minRtBCT = new HashedMap();
		final Map<Pair<String, String>, Double> maxRtBCT = new HashedMap();
		
		for(final PatronageStatus type: PatronageStatus.values()) {
			mapTotalNumberPI.put(type, this.repository.totalNumberOfPatronagesByStatus(type));
			mapAveragePD.put(type, this.repository.averageBugdetProposedPatronage(type));
			mapDeviationPD.put(type, this.repository.deviationBugdetPatronage(type));
			mapMinumumPD.put(type, this.repository.minimumBugdetPatronage(type));
			mapMaximumPD.put(type, this.repository.maximumBugdetPatronage(type));
		}
		
		for(final Object[] obj: this.repository.averageRetailPriceItemsByC(ItemType.TOOL)) {
			avrRtBC.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		
		for(final Object[] obj: this.repository.deviationRetailPriceItemsByC(ItemType.TOOL)) {
			dvaRtBC.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		
		for(final Object[] obj: this.repository.minRetailPriceItemsByC(ItemType.TOOL)) {
			minRtBC.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		
		for(final Object[] obj: this.repository.maxRetailPriceItemsByC(ItemType.TOOL)) {
			maxRtBC.put(obj[0].toString(), Double.valueOf(obj[1].toString()));
		}
		
		for(final Object[] obj: this.repository.averageRetailPriceItemsByTC(ItemType.TOOL)) {
			avrRtBCT.put(Pair.of(obj[0].toString(), obj[1].toString()), Double.valueOf(obj[2].toString()));
		}
		
		for(final Object[] obj: this.repository.deviationRetailPriceItemsByTC(ItemType.TOOL)) {
			dvaRtBCT.put(Pair.of(obj[0].toString(), obj[1].toString()), Double.valueOf(obj[2].toString()));
		}
		
		for(final Object[] obj: this.repository.minRetailPriceItemsByTC(ItemType.TOOL)) {
			minRtBCT.put(Pair.of(obj[0].toString(), obj[1].toString()), Double.valueOf(obj[2].toString()));
		}
		
		for(final Object[] obj: this.repository.maxRetailPriceItemsByTC(ItemType.TOOL)) {
			maxRtBCT.put(Pair.of(obj[0].toString(), obj[1].toString()), Double.valueOf(obj[2].toString()));
		}
		
		result.setTotalNumberOfTools(this.repository.totalItems(ItemType.TOOL));
		result.setTotalNumberOfComponents(this.repository.totalItems(ItemType.COMPONENT));
		result.setAverageRetailPriceOfComponentsByTechnologyAndCurrency(avrRtBCT);
		result.setDeviationRetailPriceOfComponentsByTechnologyAndCurrency(dvaRtBCT);
		result.setMinimumRetailPriceOfComponentsByTechnologyAndCurrency(minRtBCT);
		result.setMaximumRetailPriceOfComponentsByTechnologyAndCurrency(maxRtBCT);
		result.setAverageRetailPriceOfToolsByCurrency(avrRtBC);
		result.setDeviationRetailPriceOfToolsByCurrency(dvaRtBC);
		result.setMinimumRetailPriceOfToolsByCurrency(minRtBC);
		result.setMaximumRetailPriceOfToolsByCurrency(maxRtBC);
		result.setTotalNumberOfPatronagesByStatus(mapTotalNumberPI);
		result.setAveragePatronagesBudgetByStats(mapAveragePD);
		result.setDeviationPatronagesBudgetByStats(mapDeviationPD);
		result.setMinimumPatronagesBudgetByStats(mapMinumumPD);
		result.setMaximumPatronagesBudgetByStats(mapMaximumPD);
		return result;
	}
	

}
