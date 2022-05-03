/*
 * AuthenticatedAnnouncementController.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.items;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.items.Item;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
@RequestMapping("/inventor/item/")
public class InventorItemController extends AbstractController<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorComponentListService	listComponentService;
	
	@Autowired
	protected InventorToolListService	listToolService;

	@Autowired
	protected InventorItemShowService	showService;
	
	@Autowired
	protected InventorToolCreateService	createToolService;
	
	@Autowired
	protected InventorComponentCreateService createComponentService;
	
	@Autowired
	protected InventorItemDeleteService	deleteService;
	
	@Autowired
	protected InventorItemUpdateService	updateService;
	
	@Autowired
	protected InventorItemPublishService publishService;

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-component","list", this.listComponentService);
		super.addCommand("list-tool", "list", this.listToolService);
		super.addCommand("show", this.showService);
		
		super.addCommand("create-tool", "create", this.createToolService);
		super.addCommand("create-component", "create", this.createComponentService);
		super.addCommand("delete", this.deleteService);
		super.addCommand("update", this.updateService);
		super.addCommand("publish", "update", this.publishService);
	}

}
