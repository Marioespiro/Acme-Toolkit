package acme.features.patron.dashboard;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;
@Repository
public interface PatronDashboardRepository extends AbstractRepository {
@Query("select count(t) from Patronage t where t.status = :type")
Integer					totalNumberOfPatronages(Enum<PatronageStatus> type);

@Query("select distinct t.budget.currency from Patronage t")
Collection<String> currencies();

@Query("select avg(t.budget.amount) from Patronage t where t.status = :type and t.budget.currency = :currency ")
Double						averageBugdetProposedPatronage(Enum<PatronageStatus> type, String currency);


}
