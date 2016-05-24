package by.bogdevich.training.airline.webapp.page.admin.city;

import javax.inject.Inject;

import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

public class CityEditPage extends AbstractPage {

	@Inject
	private CityService cityService;

	private City city;

	public CityEditPage() {
		super();
	}

	public CityEditPage(City city) {
		super();
		this.city = city;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
	}
}
