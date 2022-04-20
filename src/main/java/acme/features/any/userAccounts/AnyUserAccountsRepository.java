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


}
