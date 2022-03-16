package acme.features.patron.dashboard;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.entities.dashboard.PatronDashboard;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

public class PatronDashboardController extends AbstractController<Patron, PatronDashboard> {
	// Internal state ---------------------------------------------------------

		@Autowired
		protected PatronDashboardShowService showService;

		// Constructors -----------------------------------------------------------


		@PostConstruct
		protected void initialise() {
//			super.addBasicCommand(BasicCommand.SHOW, this.showService);
		}

}
