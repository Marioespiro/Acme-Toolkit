package acme.features.patron.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface PatronDashboardRepository extends AbstractRepository {
@Query("select count(t) from Patronage where t.status = :type")
Integer 					totalNumberOfPatronages(Enum<PatronageStatus> type);
@Query("select avg(datediff(t.budget, t.retailPrice)) from Patronage t where t.status = :type")
Double						averageBugdetProposedPatronage(Enum<PatronageStatus> type);
@Query("select stddev(datediff(t.budget, t.retailPrice)) from Patronage t where t.status = :type")
Double						deviationBugdetPatronage(Enum<PatronageStatus> type);
@Query("select min(datediff(t.budget,t.retailPrice)) from Patronage t where t.status = :type")
Double						minimumBugdetPatronage(Enum<PatronageStatus> type);
@Query("select max(datediff(t.budget,t.retailPrice)) from Patronage t where t.status = :type")
Double						maximumBugdetPatronage(Enum<PatronageStatus> type);
}
