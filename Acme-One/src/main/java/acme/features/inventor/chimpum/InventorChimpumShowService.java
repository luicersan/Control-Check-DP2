package acme.features.inventor.chimpum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorChimpumShowService implements AbstractShowService<Inventor, Chimpum> {

	@Autowired
	protected InventorChimpumRepository repository;

	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;

		boolean result;
		int id;
		Chimpum chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(id);
		result = chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		int id;
		Chimpum result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startPeriod", "finishPeriod", "budget", "link");
	}
}