
package acme.features.administrator.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.ItemType;
import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	
	@Query("select count(i) from Item i where i.itemType = :type")
	Integer totalItems(Enum<ItemType> type);
	@Query("select i.retailPrice.currency, i.technology, avg(i.retailPrice.amount) from Item i group by i.retailPrice.currency, i.technology")
	List<Object[]> averageRetailPriceItemsByTC(Enum<ItemType> type);

	@Query("select i.retailPrice.currency, i.technology, stddev(i.retailPrice.amount) from Item i group by i.retailPrice.currency, i.technology")
	List<Object[]> deviationRetailPriceItemsByTC(Enum<ItemType> type);

	@Query("select i.retailPrice.currency, i.technology, min(i.retailPrice.amount) from Item i group by i.retailPrice.currency, i.technology")
	List<Object[]> minRetailPriceItemsByTC(Enum<ItemType> type);

	@Query("select i.retailPrice.currency, i.technology, max(i.retailPrice.amount) from Item i group by i.retailPrice.currency, i.technology")
	List<Object[]> maxRetailPriceItemsByTC(Enum<ItemType> type);
	
	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount) from Item i group by i.retailPrice.currency")
	List<Object[]> averageRetailPriceItemsByC(Enum<ItemType> type);

	@Query("select i.retailPrice.currency, stddev(i.retailPrice.amount) from Item i group by i.retailPrice.currency")
	List<Object[]> deviationRetailPriceItemsByC(Enum<ItemType> type);

	@Query("select i.retailPrice.currency, min(i.retailPrice.amount) from Item i group by i.retailPrice.currency")
	List<Object[]> minRetailPriceItemsByC(Enum<ItemType> type);
	
	@Query("select i.retailPrice.currency, max(i.retailPrice.amount) from Item i group by i.retailPrice.currency")
	List<Object[]> maxRetailPriceItemsByC(Enum<ItemType> type);
	
	@Query("select count(t) from Patronage t where t.status = :type")
	Integer totalNumberOfPatronagesByStatus(Enum<PatronageStatus> type);

	@Query("select avg(t.budget.amount) from Patronage t where t.status = :type")
	Double averageBugdetProposedPatronage(Enum<PatronageStatus> type);
	
	@Query("select stddev(t.budget.amount) from Patronage t where t.status = :type")
	Double deviationBugdetPatronage(Enum<PatronageStatus> type);
	
	@Query("select min(t.budget.amount) from Patronage t where t.status = :type")
	Double minimumBugdetPatronage(Enum<PatronageStatus> type);
	
	@Query("select max(t.budget.amount) from Patronage t where t.status = :type")
	Double maximumBugdetPatronage(Enum<PatronageStatus> type);
}
