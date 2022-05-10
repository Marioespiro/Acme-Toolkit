package acme.features.inventor.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);

	@Query("select p from Patronage p where p.inventor.id = :inventorId")
	Collection<Patronage> findAllPatronagesByInventorId(int inventorId);
	
	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);

//	@Query("select i from Inventor where i.id = :id")
//	Inventor findOneInventorById(int id);
	
}
