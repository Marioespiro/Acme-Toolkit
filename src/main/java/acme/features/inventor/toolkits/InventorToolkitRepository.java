package acme.features.inventor.toolkits;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.Item;
import acme.entities.quantities.Quantity;
import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorToolkitRepository extends AbstractRepository {

	@Query("select t from Toolkit t where t.id = :id")
	Toolkit findToolkitById(int id);
	
	@Query("select distinct t from Toolkit t where t.inventor.id = :inventorId")
	Collection<Toolkit> findAllTookitsByInventorId(int inventorId);
	
	@Query("select sum(q.amount * i.retailPrice.amount) as suma, i.retailPrice.currency as currency from Quantity q, Item i where q.toolkit.id = :id and q.item = i.id group by i.retailPrice.currency")
	Collection<Object[]> retailPriceByCurrency(Integer id);
	
	@Query("select qty from Quantity qty where qty.toolkit.id = :id")
	Collection<Quantity> findAllQuantitiesByToolkitId(int id);
	
	@Modifying
	@Query("delete from Quantity q where q.id = :id")
	void deleteQuantityById(int id);
	
	@Query("select t from Toolkit t where t.code = :code")
	Toolkit findToolkitByCode(String code);
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findOneInventorById(int id);
	
	@Query("select i from Item i where i.inventor.id = :inventorid and i.itemType = acme.entities.items.ItemType.TOOL")
	Collection<Item> findToolsByInvertor(Integer inventorid);

	@Query("select i from Item i where i.inventor.id = :inventorid and i.itemType = acme.entities.items.ItemType.COMPONENT")
	Collection<Item> findComponentsByInvertor(Integer inventorid);
	
	@Query("select i from Item i where i.name = :name and i.itemType = acme.entities.items.ItemType.COMPONENT")
	Item findComponentsByName(String name);
	
	@Query("select i from Item i where i.name = :name and i.itemType = acme.entities.items.ItemType.TOOL")
	Item findToolsByName(String name);
}
