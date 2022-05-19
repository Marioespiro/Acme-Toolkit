package acme.features.patron.patronage;

import java.util.Collection;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage_reports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.entities.system_configurations.SystemConfiguration;
import acme.framework.repositories.AbstractRepository;
import acme.roles.Inventor;
import acme.roles.Patron;

@Repository
public interface PatronPatronageRepository extends AbstractRepository {

	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
	
	@Modifying
	@Query("delete from PatronageReport pr where pr.id = :id")
	void deletePatronageReportById(int id);

	@Query("select p from Patronage p where p.patron.id = :patronId")
	Collection<Patronage> findAllPatronagesByPatronId(int patronId);
	
	@Query("select pr from PatronageReport pr where pr.patronage.id = :patronageId")
	Collection<PatronageReport> findAllPatronageReportsByPatronageId(int patronageId);

	@Query("select p from Patronage p where p.code = :code")
	Patronage findPatronageByCode(String code);

	@Query("select p from Patron p where p.id = :id")
	Patron findOnePatronById(int id);
	
	@Query("select sc from SystemConfiguration sc")
	Collection<SystemConfiguration> findAllConfigurations();
	
	@Query("select i from Inventor i")
	Collection<Inventor> findInventors();
	
	@Query("select i from Inventor i where i.id = :id")
	Inventor findInventorById(Integer id);
	
}