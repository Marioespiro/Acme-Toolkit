package acme.features.any.user_accounts;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.roles.UserRole;
import acme.framework.services.AbstractListService;

@Service
public class AnyUserAccountstListService implements AbstractListService<Any, UserAccount>{

	// Internal state ---------------------------------------------------------

		@Autowired
		protected AnyUserAccountsRepository repository;
		
	// AbstractListService<Any, UserAccount> interface --------------
		
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public Collection<UserAccount> findMany(final Request<UserAccount> request) {
		assert request != null;
		final Collection<UserAccount> result = new ArrayList<>();	
		Collection<UserAccount> aux;
		aux = this.repository.findAllUserAccounts();		
		Boolean isAdmin = false;
		for(final UserAccount ua : aux) {
			for(final UserRole ur: ua.getRoles()) {
				if(ur.getAuthorityName().equals("Administrator")) {
					isAdmin=true;
				}
			}
			if(Boolean.TRUE.equals(!isAdmin) && !ua.isAnonymous()) {
				result.add(ua);
			}
		}
		return result;
	}

	@Override
	public void unbind(final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		model.setAttribute("roles", entity.getAuthorityString());
		request.unbind(entity, model, "username");
	}
}
