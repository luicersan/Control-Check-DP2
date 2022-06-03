package acme.features.inventor.item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.Duboa;
import acme.entities.Item;
import acme.framework.components.models.Model;
import acme.framework.controllers.Request;
import acme.framework.services.AbstractShowService;
import acme.roles.Inventor;

@Service
public class InventorItemShowService implements AbstractShowService<Inventor, Item> {

	@Autowired
	protected InventorItemRepository repository;

	@Override
	public boolean authorise(final Request<Item> request) {
		assert request != null;

		boolean result;
		int id;
		Item item;
		
		id = request.getModel().getInteger("id");
		item = this.repository.findOneItemById(id);
		result = item != null && item.getInventor().getId() == request.getPrincipal().getActiveRoleId();

		return result;
	}

	@Override
	public Item findOne(final Request<Item> request) {
		assert request != null;

		int id;
		Item result;
		
		id = request.getModel().getInteger("id");
		result = this.repository.findOneItemById(id);

		return result;
	}
	
	@Override
	public void unbind(final Request<Item> request, final Item entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "type", "name", "code", "technology", "description", "retailPrice", "link", "publish");
		// Detect if is any duboas
		final List<Duboa> duboas = (List<Duboa>) this.repository.itemHasAnyDuboas(entity.getId());
		if(!duboas.isEmpty()) {
			model.setAttribute("hasDuboa", true);
			model.setAttribute("duboaId", duboas.get(0).getId());
		}
	}
}