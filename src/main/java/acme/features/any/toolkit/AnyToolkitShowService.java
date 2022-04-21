package acme.features.any.toolkit;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.roles.Any;
import acme.framework.services.AbstractShowService;

@Service
public class AnyToolkitShowService implements AbstractShowService<Any, Toolkit> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnyToolkitRepository repository;


	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;

		return true;
	}

	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		request.unbind(entity, model, "code", "title", "description", "assemblyNotes", "link");
		int id;
		id = request.getModel().getInteger("id");
		final Collection<Object[]> retailPrice;
		retailPrice = this.repository.retailPriceByCurrency(id);
		String res = "";
		int i = 0;
		for(final Object[] obj: retailPrice) {
			if(i == 0) {
				res =res.concat(obj[0].toString()).concat(obj[1].toString());
			}else {
				res = res.concat("+").concat(obj[0].toString()).concat(obj[1].toString());
			}
			i++;
			
		}
		model.setAttribute("retailPrice", res);
	}

	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result;
		
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);
		
		

		return result;
	}

}

