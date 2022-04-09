package acme.features.authenticated.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AuthenticatedInventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);

	@Query("select p from Patronage p where p.inventor.id = :inventorId")
	Collection<Patronage> findAllPatronagesByInventorId(int inventorId);

//	@Query("select i from Item i where i.itemType = acme.entities.items.ItemType.TOOL")
//	Collection<Item> findAllTools();
	
}
