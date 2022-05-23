package acme.features.inventor.quantity;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

public interface InventorQuantityRepository extends AbstractRepository {
	
	@Query("select q from Quantity q where q.id = :id")
	Quantity findQuantityById(int id);

	@Query("select q from Quantity q where q.toolkit.id = :id")
	Collection<Quantity> findQuantityByToolkit(int id);
	
	@Query("select distinct q from Quantity q where q.item.inventor.id = :inventorId")
	Collection<Quantity> findAllQuantitiesByInventorId(int inventorId);
	
	@Query("select q.toolkit from Quantity q where q.id = :id")
	Toolkit findToolkitByQuantity(int id);
	
	@Query("select i from Item i where i.inventor.id = :inventorid and i.itemType = acme.entities.items.ItemType.TOOL")
	Collection<Item> findToolsByInvertor(Integer inventorid);

	@Query("select i from Item i where i.inventor.id = :inventorid and i.itemType = acme.entities.items.ItemType.COMPONENT")
	Collection<Item> findComponentsByInvertor(Integer inventorid);
	
	@Query("select i from Item i where i.name = :name and i.itemType = acme.entities.items.ItemType.COMPONENT")
	Item findComponentsByName(String name);
	
	@Query("select i from Item i where i.name = :name and i.itemType = acme.entities.items.ItemType.TOOL")
	Item findToolsByName(String name);
	
	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select i from Item i where i.name = :name")
	Item findItemByName(String name);
	
	@Query("select i from Item i where i.inventor.id = :inventorid")
	Collection<Item> findItemsByInventor(Integer inventorid);
	
	@Query("select q.item from Quantity q where q.id = :id")
	Item findItemsByQuantity(Integer id);
	
}
