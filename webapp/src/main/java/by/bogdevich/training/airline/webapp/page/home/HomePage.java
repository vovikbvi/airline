package by.bogdevich.training.airline.webapp.page.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.common.AirportChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.flight.panel.FlightListPanel;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel.FlightCatalogListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;
import by.bogdevich.training.airline.webapp.page.search.SearchPage;

public class HomePage extends AbstractPage {

	@Inject
	private CityService cityService;

	private String selectedCityStart;
	private String selectedCityFinish;

	public HomePage() {
		super();
	}

	protected void onInitialize() {
		super.onInitialize();

		add(new Link("test") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage3());
			}
		});

		List<String> cityList = new ArrayList<String>();

		for (City city : new ArrayList<City>(cityService.getAll())) {
			cityList.add(city.getName());
		}

		add(new FeedbackPanel("feedback"));

		DropDownChoice<String> listSitesStart = new DropDownChoice<String>("sitesStart",
				new PropertyModel<String>(this, "selectedCityStart"), cityList);
		listSitesStart.setRequired(true);

		DropDownChoice<String> listSitesFinish = new DropDownChoice<String>("sitesFinish",
				new PropertyModel<String>(this, "selectedCityFinish"), cityList);
		listSitesFinish.setRequired(true);

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {

				info("Selected search engine : " + selectedCityStart);
				info("Selected search engine : " + selectedCityFinish);

				setResponsePage(new SearchPage(selectedCityStart, selectedCityFinish));
			}
		};

		add(form);
		form.add(listSitesStart);
		form.add(listSitesFinish);

	}

}
