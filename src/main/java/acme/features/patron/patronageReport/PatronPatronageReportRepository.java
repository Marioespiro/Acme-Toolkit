package acme.features.patron.patronageReport;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronageReports.PatronageReport;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface PatronPatronageReportRepository extends AbstractRepository {
	
	@Query("select pr from PatronageReport pr where pr.id = :id")
	PatronageReport findPatronageReportById(int id);
	
	@Query("select pr from PatronageReport pr where pr.patronage.patron.id = :patronId")
	Collection<PatronageReport> findAllPatronageReportsByPatronId(int patronId);

}
