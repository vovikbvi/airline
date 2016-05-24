package by.bogdevich.training.airline.webapp.page.admin.manufactured;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.service.ManufacturedPlainService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class ManufacturedEditPage extends AbstractPage {

	@Inject
	private ManufacturedPlainService manufacturedPlainService;

	private ManufacturedPlane manufacturedPlane;

	public ManufacturedEditPage() {
		super();
	}

	public ManufacturedEditPage(ManufacturedPlane manufacturedPlane) {
		super();
		this.manufacturedPlane = manufacturedPlane;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		
		
	}

}
