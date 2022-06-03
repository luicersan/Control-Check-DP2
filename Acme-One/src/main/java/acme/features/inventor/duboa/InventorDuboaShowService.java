package acme.features.inventor.duboa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Duboa;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorDuboaShowService implements AbstractShowService<Inventor, Duboa> {

	@Autowired
	protected InventorDuboaRepository repository;

	@Override
	public boolean authorise(final Request<Duboa> request) {
		assert request != null;

		boolean result;
		int id;
		Duboa chimpum;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findOneDuboaById(id);
		result = chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Duboa findOne(final Request<Duboa> request) {
		assert request != null;

		int id;
		Duboa result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneDuboaById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Duboa> request, final Duboa entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "code", "name", "summary", "startPeriod", "finishPeriod", "allotment", "additionalInfo");
	}
}