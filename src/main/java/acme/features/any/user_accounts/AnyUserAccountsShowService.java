package acme.features.any.user_accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyUserAccountsShowService implements AbstractShowService<Any, UserAccount>{
	
	@Autowired
	protected AnyUserAccountsRepository repository;
	
	@Override
	public boolean authorise(final Request<UserAccount> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public UserAccount findOne(final Request<UserAccount> request) {
		assert request != null;
		
		UserAccount result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneUserAccountById(id);
		return result;
	}

	@Override
	public void unbind( final Request<UserAccount> request, final UserAccount entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		final String name=entity.getIdentity().getName();
		final String surname=entity.getIdentity().getSurname();
		final String email=entity.getIdentity().getEmail();
		model.setAttribute("name", name);
		model.setAttribute("surname", surname);
		model.setAttribute("email", email);
		request.unbind(entity, model, "username");
	}
}
