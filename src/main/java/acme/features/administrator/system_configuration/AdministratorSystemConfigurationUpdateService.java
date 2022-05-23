package acme.features.administrator.system_configuration;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.system_configurations.SystemConfiguration;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractUpdateService;
import acme.util.SpamFilterService;

@Service
public class AdministratorSystemConfigurationUpdateService implements AbstractUpdateService<Administrator, SystemConfiguration> {
	
	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorSystemConfigurationRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractUpdateService<Inventor, Item> interface ---------------------------

	@Override
	public boolean authorise(final Request<SystemConfiguration> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms", "weakSpamThreshold");
	}

	@Override
	public void unbind(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "systemCurrency", "acceptedCurrencies", "strongSpamTerms", "strongSpamThreshold", "weakSpamTerms", "weakSpamThreshold");
	}

	@Override
	public SystemConfiguration findOne(final Request<SystemConfiguration> request) {
		assert request != null;
		SystemConfiguration result;
		int id;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findSystemConfigurationById(id);
		return result;
	}

	@Override
	public void validate(final Request<SystemConfiguration> request, final SystemConfiguration entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		if(!errors.hasErrors("systemCurrency")) {
			final List<String> currencies = Arrays.asList(request.getModel().getString("acceptedCurrencies").split(";"));
			errors.state(request, currencies.contains(request.getModel().getString("systemCurrency")), "systemCurrency", "admin.systemConfiguration.form.error.currencyNotBelonging");
		}
		
		if(!errors.hasErrors("acceptedCurrencies")) {
			final List<String> currencies = Arrays.asList(request.getModel().getString("acceptedCurrencies").split(";"));
			Boolean ac = true;
			for(final String currency : currencies) {
				if(currency.length()!=3) {
					ac = false;
				}
			}
			errors.state(request, ac, "acceptedCurrencies", "admin.systemConfiguration.form.error.wrongCurrencies");
		}

	}

	@Override
	public void update(final Request<SystemConfiguration> request, final SystemConfiguration entity) {
		assert request != null;
		assert entity != null;

		final String currencies = entity.getAcceptedCurrencies();
		final String currency = entity.getSystemCurrency();
		entity.setSystemCurrency(currency.toUpperCase());
		entity.setAcceptedCurrencies(currencies.toUpperCase());
		this.repository.save(entity);
		
	}


}
