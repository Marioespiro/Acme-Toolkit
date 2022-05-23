package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);

	@Query("select p from Patronage p where p.inventor.id = :inventorId and p.isPublished = true")
	Collection<Patronage> findAllPatronagesByInventorId(int inventorId);

	@Query("select i from Inventor i")
	Collection<Inventor> findInventors();
	
	
}
