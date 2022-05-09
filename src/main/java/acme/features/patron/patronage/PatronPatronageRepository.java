package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);

	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findAllPatronagesByPatronId(int patronId);

	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);

	@Query("select p from Patron p where p.id = :id")
	Patron findOnePatronById(int id);
	
}