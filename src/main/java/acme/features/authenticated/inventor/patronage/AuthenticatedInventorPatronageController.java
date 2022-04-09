package acme.features.authenticated.inventor.patronage;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.patronages.Patronage;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("/inventor/patronage/")
public class AuthenticatedInventorPatronageController extends AbstractController<Inventor, Patronage> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AuthenticatedInventorPatronageListService	listInventorPatronageService;

//	@Autowired
//	protected InventorPatronageListService	listInventorPatronageService;

	@Autowired
	protected AuthenticatedInventorPatronageShowService	showInventorPatronageService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		//super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("list-tool","list", this.listInventorPatronageService);
		super.addCommand("show", this.showInventorPatronageService);
	}
	
}