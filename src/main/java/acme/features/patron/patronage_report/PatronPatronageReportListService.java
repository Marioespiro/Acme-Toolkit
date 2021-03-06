package acme.features.patron.patronage_report;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronage_reports.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Patron;

@Service
public class PatronPatronageReportListService implements AbstractListService<Patron, PatronageReport>{

	// Internal state
	
	@Autowired
	protected PatronPatronageReportRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;
		
		Collection<PatronageReport> result;
		Principal principal;
		
		principal = request.getPrincipal();
		result = this.repository.findAllPatronageReportsByPatronId(principal.getActiveRoleId());
		
		return result;
	}
	
	@Override
	public void unbind(final Request<PatronageReport> request, final PatronageReport entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "creationMoment", "memorandum", "link", "automaticSequenceNumber");
	}
	
}
