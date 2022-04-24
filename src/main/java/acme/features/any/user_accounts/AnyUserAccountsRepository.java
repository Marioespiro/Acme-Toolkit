package acme.features.any.userAccounts;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;

import acme.framework.entities.UserAccount;
import acme.framework.repositories.AbstractRepository;

public interface AnyUserAccountsRepository extends AbstractRepository{
	
	@Query("select ua from UserAccount ua  where ua.enabled = true")
	Collection<UserAccount> findAllUserAccounts();

	@Query("select ua from UserAccount ua where ua.id = :id")
	UserAccount findOneUserAccountById(int id);
	
	@Query("select  ua from UserAccount ua join fetch ua.roles r where ua.enabled = true and (type(r) = Inventor or type(r) = Patron) and Administrator not in (select type(r) from UserAccount ua2 join ua2.roles r where ua2.id = ua.id)")
	Collection<UserAccount> findAllUserAccounts2();

}
