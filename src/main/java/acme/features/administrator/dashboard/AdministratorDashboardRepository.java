
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
	Integer totalItems(ItemType type);
	@Query("select i.retailPrice.currency, i.technology, avg(i.retailPrice.amount), stddev(i.retailPrice.amount),  min(i.retailPrice.amount), max(i.retailPrice.amount) from Item i group by i.retailPrice.currency, i.technology")
	List<Object[]> operationsRetailPriceItemsByTC(ItemType type);

	@Query("select i.retailPrice.currency, avg(i.retailPrice.amount), stddev(i.retailPrice.amount), min(i.retailPrice.amount), max(i.retailPrice.amount) from Item i group by i.retailPrice.currency")
	List<Object[]> operationsRetailPriceItemsByC(ItemType type);

	@Query("select count(t), avg(t.budget.amount), stddev(t.budget.amount), min(t.budget.amount), max(t.budget.amount) from Patronage t where t.status = :type")
	List<Object[]> operationsPatronagesByStatus(PatronageStatus type);

}
