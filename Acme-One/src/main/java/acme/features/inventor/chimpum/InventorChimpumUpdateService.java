package acme.features.inventor.chimpum;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Chimpum;
import acme.framework.components.models.Model;
import acme.framework.controllers.Errors;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractUpdateService;
import acme.roles.Inventor;

@Service
public class InventorChimpumUpdateService implements AbstractUpdateService<Inventor, Chimpum> {
	
	@Autowired
	protected InventorChimpumRepository repository;
	
	@Override
	public boolean authorise(final Request<Chimpum> request) {
		assert request != null;
		
		boolean result;
		Chimpum chimpum;
		int id;
		
		id = request.getModel().getInteger("id");
		chimpum = this.repository.findOneChimpumById(id);
		result = chimpum.getItem().getInventor().getId() == request.getPrincipal().getActiveRoleId();
		return result;
	}
	
	@Override
	public void bind(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		
		request.bind(entity, errors, "code", "creationMoment", "title", "description", "budget", "link");
	}
	
	@Override
	public void unbind(final Request<Chimpum> request, final Chimpum entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;
		
		request.unbind(entity, model, "code", "creationMoment", "title", "description", "startPeriod", "finishPeriod", "budget", "link");
	}
	
	@Override
	public Chimpum findOne(final Request<Chimpum> request) {
		assert request != null;

		Chimpum result;
		int id;
		id = request.getModel().getInteger("id");
		result = this.repository.findOneChimpumById(id);

		return result;
	}
	
	@Override
	public void validate(final Request<Chimpum> request, final Chimpum entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
		if(!errors.hasErrors("budget")) {
			final String upperCaseCurrency = entity.getBudget().getCurrency().toUpperCase();
			boolean accepted = false;
			
			// Manage likely currencies
			for (final String acceptedCurrency : this.repository.findConfiguration().getAcceptedCurrencies().toUpperCase().split("[.]")) {
				if (upperCaseCurrency.equals(acceptedCurrency)) {
					accepted = true;
					break;
				}
			}
			errors.state(request, entity.getBudget().getAmount() > 0, "budget", "inventor.chimpum.form.error.negative-budget");
			errors.state(request, accepted, "budget", "inventor.chimpum.form.error.non-accepted-currency");
		}
	}
	
	@Override
	public void update(final Request<Chimpum> request, final Chimpum entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}
}