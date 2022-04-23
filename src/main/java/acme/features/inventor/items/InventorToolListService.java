/*
 * AuthenticatedAnnouncementListService.java
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

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.items.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorToolListService implements AbstractListService<Inventor, Item> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorItemRepository repository;

	// AbstractListService<Administrator, Item> interface --------------


	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;
		int id;
		Principal principal;
		principal = request.getPrincipal();
		if(request.getModel().hasAttribute("toolkitId")) {
			final List<Integer> toolkits = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());;
			id = request.getModel().getInteger("toolkitId");
			if(toolkits.contains(id)) {
				return true;
			}
			return false;
			
		}else {
			return true;
		}
		
		
	}
	
	@Override
	public Collection<Item> findMany(final Request<Item> request) {
		assert request != null;
		Integer id;
		Collection<Item> result;
		if(request.getModel().hasAttribute("toolkitId")) {
			id = request.getModel().getInteger("toolkitId");
			result = this.repository.findToolsByToolkit(id);
			return result;
		}else {
			id = request.getPrincipal().getActiveRoleId();
			result = this.repository.findToolsByInvertor(id);
			return result;
		}
	}

	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "name", "technology", "retailPrice");
	}

	

}
