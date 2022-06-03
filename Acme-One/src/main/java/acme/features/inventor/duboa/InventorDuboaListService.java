/*
 * InventorDuboaListService.java
 *
 * Copyright (C) 2012-2022 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.inventor.duboa;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Duboa;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractListService;
import acme.roles.Inventor;

@Service
public class InventorDuboaListService implements AbstractListService<Inventor, Duboa> {

	@Autowired
	protected InventorDuboaRepository repository;

	@Override
	public boolean authorise(final Request<Duboa> request) {
		assert request != null;
		
		boolean result;
		
		result = request.getPrincipal().hasRole(Inventor.class); 

		return result;
	}

	@Override
	public Collection<Duboa> findMany(final Request<Duboa> request) {
		assert request != null;
		
		Collection<Duboa> result;
		result = this.repository.findAllDuboasByInventorId(request.getPrincipal().getActiveRoleId());
		
		return result;
	}
	
	@Override
	public void unbind(final Request<Duboa> request, final Duboa entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "name", "startPeriod", "finishPeriod");
	}
}