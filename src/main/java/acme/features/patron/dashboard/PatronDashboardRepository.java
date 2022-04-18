package acme.features.patron.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface PatronDashboardRepository extends AbstractRepository {
	@Query("select count(t) from Patronage t where t.status = :type")
	Integer	totalNumberOfPatronages(PatronageStatus type);
	
	@Query("select t.budget.currency, avg(t.budget.amount) from Patronage t where t.status = :type")
	Collection<Object[]> averageBugdetPatronage(PatronageStatus type);
	
	@Query("select t.budget.currency, stddev(t.budget.amount) from Patronage t where t.status = :type")
	Collection<Object[]> deviationBugdetPatronage(PatronageStatus type);
	
	@Query("select t.budget.currency, min(t.budget.amount) from Patronage t where t.status = :type")
	Collection<Object[]> minBugdetPatronage(PatronageStatus type);
	
	@Query("select t.budget.currency, max(t.budget.amount) from Patronage t where t.status = :type")
	Collection<Object[]> maxBugdetPatronage(PatronageStatus type);
}
