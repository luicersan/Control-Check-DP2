package acme.features.inventor.duboa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Duboa;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorDuboaUpdateService implements AbstractUpdateService<Inventor, Duboa> {
	
	@Autowired
	protected InventorDuboaRepository repository;
	
	@Override
	public boolean authorise(final Request<Duboa> request) {
		assert request != null;
		
		boolean result;
		Duboa chimpum;
		int id;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findOneDuboaById(id);
		result = chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		return result;
	}
	
	@Override
	public void bind(final Request<Duboa> request, final Duboa entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "name", "summary", "allotment", "additionalInfo");
	}
	
	@Override
	public void unbind(final Request<Duboa> request, final Duboa entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "name", "summary", "startPeriod", "finishPeriod", "allotment", "additionalInfo");
	}
	
	@Override
	public Duboa findOne(final Request<Duboa> request) {
		assert request != null;

		Duboa result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneDuboaById(id);

		return result;
	}
	
	@Override
	public void validate(final Request<Duboa> request, final Duboa entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("budget")) {
			final String upperCaseCurrency = entity.getAllotment().getCurrency().toUpperCase();
			boolean accepted = false;
			
			// Manage likely currencies
			for (final String acceptedCurrency : this.repository.findConfiguration().getAcceptedCurrencies().toUpperCase().split("[.]")) {
				if (upperCaseCurrency.equals(acceptedCurrency)) {
					accepted = true;
					break;
				}
			}
			errors.state(request, entity.getAllotment().getAmount() > 0, "budget", "inventor.chimpum.form.error.negative-budget");
			errors.state(request, accepted, "budget", "inventor.chimpum.form.error.non-accepted-currency");
		}
	}
	
	@Override
	public void update(final Request<Duboa> request, final Duboa entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}