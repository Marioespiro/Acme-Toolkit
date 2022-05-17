/*
 * AuthenticatedAnnouncementShowService.java
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

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractShowService<Administrator, Announcement> interface --------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		int id;
		Principal principal;
		Item item;
		principal = request.getPrincipal();
		id = request.getModel().getInteger("id");
		item = this.repository.findItemById(id);
		
		if (request.getModel().hasAttribute("toolkitId")) {
			final List<Integer> toolkits = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
			id = request.getModel().getInteger("toolkitId");
			if (!toolkits.contains(id)) {
				return false;
			}
		}
		
		if(item.getInventor().getUserAccount().getId() != principal.getAccountId()) {
			return false;
		}
		
		return true;

	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "code", "description", "technology", "retailPrice", "link", "isPublished");
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		Item result;
		int id;

		id = request.getModel().getInteger("id");
		result = this.repository.findItemById(id);

		return result;
	}

}
