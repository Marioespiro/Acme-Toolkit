package acme.features.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("/inventor/patronage/")
public class InventorPatronageController extends AbstractController<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorPatronageListService	listService;

	@Autowired
	protected InventorPatronageShowService	showService;
	
	@Autowired
	protected InventorPatronageDenyService denyService;
	
	@Autowired
	protected InventorPatronageAcceptService acceptService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("accept", "update", this.acceptService);
		super.addCommand("deny", "update", this.denyService);
	}
	
}