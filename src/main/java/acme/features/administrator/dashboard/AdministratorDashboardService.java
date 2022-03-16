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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.forms.AdministratorDashboard;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;

@Service
public class AdministratorDashboardService implements AbstractCreateService<Administrator, AdministratorDashboard> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorDashboardRepository repository;


	@Override
	public boolean authorise(final Request<AdministratorDashboard> request) {
		assert request != null;

		boolean result;

		result = !request.getPrincipal().hasRole(Administrator.class);

		return result;
	}

	@Override
	public void bind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "totalItems", "averageRetailPriceItems", "deviationRetailPriceItems", "minRetailPriceItems", "maxRetailPriceItems", "averageBudgetPatronages", "deviationBudgetPatronages", "minBudgetPatronages", "maxBudgetPatronages");

	}

	@Override
	public void unbind(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "totalItems", "averageRetailPriceItems", "deviationRetailPriceItems", "minRetailPriceItems", "maxRetailPriceItems", "averageBudgetPatronages", "deviationBudgetPatronages", "minBudgetPatronages", "maxBudgetPatronages","totalItems", "averageRetailPriceItems", "deviationRetailPriceItems", "minRetailPriceItems", "maxRetailPriceItems", "averageBudgetPatronages", "deviationBudgetPatronages", "minBudgetPatronages", "maxBudgetPatronages");

	}

	@Override
	public AdministratorDashboard instantiate(final Request<AdministratorDashboard> request) {
		return null;
		// TODO Auto-generated method stub
	}

	@Override
	public void validate(final Request<AdministratorDashboard> request, final AdministratorDashboard entity, final Errors errors) {
		// TODO Auto-generated method stub

	}

	@Override
	public void create(final Request<AdministratorDashboard> request, final AdministratorDashboard entity) {
		// TODO Auto-generated method stub

	}

	// AbstractCreateService<Authenticated, Consumer> ---------------------------

}
