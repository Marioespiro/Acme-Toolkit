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

package acme.features.any.toolkit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import acme.entities.toolkit.Toolkit;
import acme.framework.controllers.AbstractController;
import acme.framework.roles.Any;

@Controller
@RequestMapping("/authenticated/toolkit/")
public class AnyToolkitController extends AbstractController<Any, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitListService	listToolkitService;
	
	@Autowired
	protected AnyToolkitShowService	showToolkitService;
	

	// Constructors -----------------------------------------------------------


	@PostConstruct
	protected void initialise() {
		super.addCommand("list-toolkit","list", this.listToolkitService);
		super.addCommand("show", this.showToolkitService);
	}

}
