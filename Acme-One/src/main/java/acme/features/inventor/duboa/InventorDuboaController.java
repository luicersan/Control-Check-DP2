package acme.features.inventor.duboa;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import acme.entities.Duboa;
import acme.framework.controllers.AbstractController;
import acme.roles.Inventor;

@Controller
public class InventorDuboaController extends AbstractController<Inventor, Duboa> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected InventorDuboaListService listService;

	@Autowired
	protected InventorDuboaShowService showService;
	
	@Autowired
	protected InventorDuboaCreateService createService;
	
	@Autowired
	protected InventorDuboaUpdateService updateService;
	
	@Autowired
	protected InventorDuboaDeleteService deleteService;
	
	// Constructors -----------------------------------------------------------

	@PostConstruct
	protected void initialise() {
		super.addCommand("list", this.listService);
		super.addCommand("show", this.showService);
		super.addCommand("create", this.createService);
		super.addCommand("update", this.updateService);
		super.addCommand("delete", this.deleteService);
	}
}