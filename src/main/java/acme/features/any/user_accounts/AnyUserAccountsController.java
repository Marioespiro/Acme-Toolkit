package acme.features.any.userAccounts;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.framework.controllers.AbstractController;
import acme.framework.entities.UserAccount;
import acme.framework.roles.Any;

@Controller
public class AnyUserAccountsController extends AbstractController<Any, UserAccount>{

	@Autowired
	protected AnyUserAccountstListService	listUserAccountsService;
	
	@Autowired
	protected AnyUserAccountsShowService showService;
	
	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listUserAccountsService);
		super.addCommand("show", this.showService);

	}

}
