
package acme.features.administrator.dashboard;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.items.ItemType;
import acme.entities.patronages.PatronageStatus;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AdministratorDashboardRepository extends AbstractRepository {

	@Query("select count(i) from Item i where i.itemType = :type")
	Integer totalItems(Enum<ItemType> type);

	@Query("select avg(i.retailPrice.amount) from Item i where i.itemType = :type")
	Double averageRetailPriceItems(Enum<ItemType> type);

	@Query("select stddev(i.retailPrice.amount) from Item i where i.itemType = :type group by i.technology, i.retailPrice.currency")
	Double deviationRetailPriceItems(Enum<ItemType> type);

	@Query("select min(i.retailPrice.amount) from Item i where i.itemType = :type group by i.technology, i.retailPrice.currency")
	Double minRetailPriceItems(Enum<ItemType> type);

	@Query("select max(i.retailPrice.amount) from Item i where i.itemType = :type group by i.technology, i.retailPrice.currency")
	Double maxRetailPriceItems(Enum<ItemType> type);

	@Query("select avg(datediff(t.budget, t.retailPrice)) from Patronage t where t.status = :type")
	Double averageBugdetProposedPatronage(Enum<PatronageStatus> type);
	
	@Query("select stddev(datediff(t.budget, t.retailPrice)) from Patronage t where t.status = :type")
	Double deviationBugdetPatronage(Enum<PatronageStatus> type);
	
	@Query("select min(datediff(t.budget,t.retailPrice)) from Patronage t where t.status = :type")
	Double minimumBugdetPatronage(Enum<PatronageStatus> type);
	
	@Query("select max(datediff(t.budget,t.retailPrice)) from Patronage t where t.status = :type")
	Double maximumBugdetPatronage(Enum<PatronageStatus> type);

	/*
	 * 
	 * 
	 * 
	 * Double averageBudgetPatronages;
	 * Double deviationBudgetPatronages;
	 * Integer minBudgetPatronages;
	 * Integer maxBudgetPatronages;
	 */
}
