package acme.features.inventor.patronage_report;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronage_reports.PatronageReport;
import acme.entities.patronages.Patronage;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface InventorPatronageReportRepository extends AbstractRepository {

	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findPatronageReportById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.inventor.id = :inventorId")
	Collection<PatronageReport> findAllPatronageReportsByInventorId(int inventorId);
	
	@Query("select p from Patronage p where p.id = :id")
	Patronage findPatronageById(int id);
}
