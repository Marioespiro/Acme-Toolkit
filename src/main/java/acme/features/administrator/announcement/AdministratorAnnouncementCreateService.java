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

package acme.features.administrator.announcement;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.announcements.Announcement;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Administrator;
import acme.framework.services.AbstractCreateService;
import acme.util.SpamFilterService;

@Service
public class AdministratorAnnouncementCreateService implements AbstractCreateService<Administrator, Announcement> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AdministratorAnnouncementRepository	repository;

	@Autowired
	protected SpamFilterService						spamFilterService;

	// AbstractCreateService<Authenticated, Consumer> ---------------------------


	@Override
	public boolean authorise(final Request<Announcement> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		request.bind(entity, errors, "title", "body", "critical", "url");

	}

	@Override
	public void unbind(final Request<Announcement> request, final Announcement entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "title", "body", "critical", "url");

	}

	@Override
	public Announcement instantiate(final Request<Announcement> request) {
		assert request != null;
		final Announcement result = new Announcement();
		result.setCreationDate(Date.from(Instant.now()));
		return result;
	}

	@Override
	public void validate(final Request<Announcement> request, final Announcement entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		errors.state(request, !this.spamFilterService.isSpam(entity.getTitle()), "title", "administrator.announcement.form.error.title");
		errors.state(request, !this.spamFilterService.isSpam(entity.getBody()), "body", "administrator.announcement.form.error.body");
		errors.state(request, !this.spamFilterService.isSpam(entity.getUrl()), "url", "administrator.announcement.form.error.url");
		errors.state(request, request.getModel().getBoolean("confirm"), "confirm", "any.chirp.form.error.must-confirm");

	}

	@Override
	public void create(final Request<Announcement> request, final Announcement entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);

	}

}
