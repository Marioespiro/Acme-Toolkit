package acme.features.inventor.patronage_report;
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

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage_reports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractCreateService;
import acme.roles.Inventor;
import acme.util.SpamFilterService;

@Service
public class InventorPatronageReportCreateService implements AbstractCreateService<Inventor, PatronageReport> {

	// Internal state ---------------------------------------------------------
	
	private final String MEMORANDUM = "memorandum";
	private final String MASTERID = "masterId";

	@Autowired
	protected InventorPatronageReportRepository repository;

	@Autowired
	protected SpamFilterService spamFilterService;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, this.MEMORANDUM, "link");

	}

	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, this.MEMORANDUM, "link");

		model.setAttribute(this.MASTERID, request.getModel().getAttribute(this.MASTERID));

	}

	@Override
	public PatronageReport instantiate(final Request<PatronageReport> request) {
		assert request != null;
		int patronageId;
		Patronage patronage;
		final PatronageReport result = new PatronageReport();
		patronageId = request.getModel().getInteger(this.MASTERID);
		patronage = this.repository.findPatronageById(patronageId);
		result.setCreationMoment(Date.from(Instant.now()));
		result.setPatronage(patronage);
		result.getAutomaticSequenceNumber();
		return result;
	}

	@Override
	public void validate(final Request<PatronageReport> request, final PatronageReport entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		if(this.spamFilterService.isSpam(entity.getMemorandum())) {
			errors.state(request, false, this.MEMORANDUM, "inventor.patronageReport.form.error.memorandum");
		}

		if(this.spamFilterService.isSpam(entity.getLink())) {
			errors.state(request, false, "link", "inventor.patronageReport.form.error.link");
		}
		
		errors.state(request, request.getModel().getBoolean("confirm"), "confirm", "inventor.patronageReport.form.error.must-confirm");

	}

	@Override
	public void create(final Request<PatronageReport> request, final PatronageReport entity) {
		assert request != null;
		assert entity != null;
		this.repository.save(entity);

	}




}
