package acme.features.inventor.patronageReport;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.patronageReports.PatronageReport;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractService;
import acme.roles.Inventor;

@Service
public class InventorPatronageReportListService implements AbstractService<Inventor, PatronageReport>{

	// Internal state
	
	@Autowired
	protected InventorPatronageReportRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<PatronageReport> request) {
		assert request != null;
		
		final boolean result;
		int masterId;
		final PatronageReport patronageReport;
		
		masterId = request.getModel().getInteger("masterId");
		return true;
	}
	
	@Override
	public Collection<PatronageReport> findMany(final Request<PatronageReport> request) {
		assert request != null;
		
		Collection<PatronageReport> result;
		int masterId;
		
		masterId = request.getModel().getInteger("masterId");
		result = this.repository.findManyPatronageReportsByMasterId(masterId);
		
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


