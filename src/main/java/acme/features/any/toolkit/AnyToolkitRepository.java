package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.entities.toolkit.Toolkit;
import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnyToolkitRepository extends AbstractRepository {

	@Query("select i from Toolkit i where i.id = :id and i.isPublished = TRUE")
	Toolkit findToolkitById(int id);

	@Query("select i from Toolkit i where i.isPublished = TRUE")
	Collection<Toolkit> findAllPublishedToolkits();
	
	@Query("select sum(q.amount * i.retailPrice.amount) as suma, i.retailPrice.currency as currency from Quantity q, Item i where q.toolkit.id = :id and q.item = i.id group by i.retailPrice.currency")
	Collection<Object[]> retailPriceByCurrency(Integer id);
}
