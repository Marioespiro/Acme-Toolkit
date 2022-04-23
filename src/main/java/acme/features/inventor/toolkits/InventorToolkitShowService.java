package acme.features.inventor.toolkits;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.toolkit.Toolkit;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.entities.AbstractEntity;
import acme.framework.entities.Principal;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorToolkitShowService implements AbstractShowService<Inventor, Toolkit>{

	// Internal state
	
	@Autowired
	protected InventorToolkitRepository repository;
	
	// Interface
	
	@Override
	public boolean authorise(final Request<Toolkit> request) {
		assert request != null;
		int id;
		Principal principal;
		principal = request.getPrincipal();
		final List<Integer> toolkits = this.repository.findAllTookitsByInventorId(principal.getActiveRoleId()).stream().map(AbstractEntity::getId).collect(Collectors.toList());
		id = request.getModel().getInteger("id");
		return toolkits.contains(id);
	}
	
	@Override
	public Toolkit findOne(final Request<Toolkit> request) {
		assert request != null;

		Toolkit result = null;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findToolkitById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Toolkit> request, final Toolkit entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "description", "link", "title", "assemblyNotes");
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
	
	
}
