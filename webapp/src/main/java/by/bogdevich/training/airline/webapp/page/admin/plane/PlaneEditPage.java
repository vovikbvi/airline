package by.bogdevich.training.airline.webapp.page.admin.plane;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class PlaneEditPage extends AbstractPage {

	@Inject
	private PlaneService planeService;

	private Plane plane;

	public PlaneEditPage() {
		super();
	}

	public PlaneEditPage(Plane plane) {
		super();
		this.plane = plane;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		
		
	}
}
