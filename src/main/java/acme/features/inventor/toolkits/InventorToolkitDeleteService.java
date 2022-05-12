package acme.features.inventor.toolkits;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * EmployerDutyDeleteService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.quantities.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractDeleteService;
import acme.roles.Inventor;

@Service
public class InventorToolkitDeleteService implements AbstractDeleteService<Inventor, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorToolkitRepository repository;

	// AbstractDeleteService<Employer, Duty> interface -------------------------


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		int id;

		Toolkit toolkit = null;
		id = request.getModel().getInteger("id");
		toolkit = this.repository.findToolkitById(id);
		Principal principal;
		principal = request.getPrincipal();
		final List<Integer> toolkits = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
		return !toolkit.isPublished() && toolkits.contains(id);
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);

		return result;
	}

	@Override
	public void bind(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors, "code", "description", "link", "title", "assemblyNotes");
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "description", "link", "title", "assemblyNotes");
		int id;
		id = request.getModel().getInteger("id");
		final Collection<Object[]> retailPrice;
		retailPrice = this.repository.retailPriceByCurrency(id);
		String res = "";
		int i = 0;
		for(final Object[] obj: retailPrice) {
			final Double aux = Double.parseDouble(obj[0].toString());
			final NumberFormat formatter = new DecimalFormat("#0.00");
			final String formattedNumber = formatter.format(aux);
			
			if(i == 0) {
				
				res =res.concat(formattedNumber).concat(obj[1].toString());
			}else {
				res = res.concat("+").concat(formattedNumber).concat(obj[1].toString());
			}
			i++;
			
		}
		model.setAttribute("retailPrice", res);
	}

	@Override
	public void validate(final Request<Toolkit> request, final Toolkit entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void delete(final Request<Toolkit> request, final Toolkit entity) {
		assert request != null;
		assert entity != null;
		
		if(!entity.isPublished()) {
			Collection<Quantity> quantities;

			quantities = this.repository.findAllDutiesByToolkitId(entity.getId());
			for (final Quantity qty : quantities) {
				this.repository.deleteQuantityById(qty.getId());
			}
			
			this.repository.delete(entity);
		}
		
		this.repository.delete(entity);
	}

}
