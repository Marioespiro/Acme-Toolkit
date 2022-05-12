package acme.features.inventor.toolkits;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorToolkitController extends AbstractController<Inventor, Toolkit>{

	// Internal state
	
	@Autowired
	protected InventorToolkitListService listService;
	
	@Autowired
	protected InventorToolkitShowService showService;
	
	@Autowired
	protected InventorToolkitDeleteService deleteService;
	
	@Autowired
	protected InventorToolkitPublishService publishService;
	
	
	// Constructors
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("publish", "update", this.publishService);
	}
}
