package acme.features.any.chirp;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.chirps.Chirp;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractCreateService;
import acme.util.SpamFilterService;

@Service
public class AnyChirpCreateService implements AbstractCreateService<Any, Chirp> {
	
	// Internal state
	
	private final String AUTHOR = "author";
	private final String TITLE = "title";
	private final String SPAM_ERROR = "any.chirp.form.error.spam";
	private final String CONFIRM = "confirm";
	
	@Autowired
	protected AnyChirpRepository repository;
	
	@Autowired
	protected SpamFilterService spamFilterService;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Chirp> request) {
		assert request != null;
		
		return true;
	}
	
	@Override
	public Chirp instantiate(final Request<Chirp> request) {
		assert request != null;
		
		Chirp result;
		
		result = new Chirp();
		result.setTitle("");
		result.setAuthor("");
		result.setBody("");
		final Date dateNow = new Date();
		final Calendar cal = Calendar.getInstance();
		cal.setTime(dateNow);
		cal.add(Calendar.MILLISECOND, -100);
		result.setCreationDate(cal.getTime());
		result.setEmail("");
		
		return result;
	}
	
	@Override
	public void bind(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, this.TITLE, this.AUTHOR, "body", "email");
	}
	
	@Override
	public void validate(final Request<Chirp> request, final Chirp entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		
		errors.state(request, !this.spamFilterService.isSpam(entity.getTitle()), this.TITLE, this.SPAM_ERROR);
		errors.state(request, !this.spamFilterService.isSpam(entity.getAuthor()), this.AUTHOR, this.SPAM_ERROR);
		errors.state(request, !this.spamFilterService.isSpam(entity.getBody()), "body", this.SPAM_ERROR);
		
		errors.state(request, request.getModel().getBoolean(this.CONFIRM), this.CONFIRM, "any.chirp.form.error.must-confirm");
	}
	
	@Override
	public void unbind(final Request<Chirp> request, final Chirp entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, this.TITLE, this.AUTHOR, "body", "email", "creationDate");
		model.setAttribute(this.CONFIRM, "false");
	}
	
	@Override
	public void create(final Request<Chirp> request, final Chirp entity) {
		assert request != null;
		assert entity != null;
		
		this.repository.save(entity);
	}
}
