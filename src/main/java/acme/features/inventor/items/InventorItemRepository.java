/*
 * AuthenticatedAnnouncementRepository.java
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

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorItemRepository extends AbstractRepository {

	@Query("select i from Item i where i.id = :id")
	Item findItemById(int id);

	@Query("select i from Item i where i.itemType = acme.entities.items.ItemType.COMPONENT")
	Collection<Item> findAllComponents();
	
	@Query("select i from Item i where i.itemType = acme.entities.items.ItemType.TOOL")
	Collection<Item> findAllTools();
	
	@Query("select distinct q.toolkit from Quantity q where q.item.inventor.id = :inventorId")
	Collection<Toolkit> findAllTookitsByInventorId(int inventorId);


}