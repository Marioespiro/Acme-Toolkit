package acme.features.patron.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Patron;

@Controller
@RequestMapping("/patron/patronage/")
public class PatronPatronageController extends AbstractController<Patron, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected PatronPatronageListService	listService;


	@Autowired
	protected PatronPatronageShowService	showService;
	
	@Autowired
	protected PatronPatronageCreateService	createPatronageService;


	@Autowired
	protected PatronPatronageDeleteService	deleteService;

	@Autowired
	protected PatronPatronageUpdateService	updateService;

	@Autowired
	protected PatronPatronagePublishService publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		
		super.addCommand("create-patronage", "create", this.createPatronageService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
	}
	
}