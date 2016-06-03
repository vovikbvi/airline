package by.bogdevich.training.airline.webapp.page.home;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.inject.Inject;

import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;

import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.schedule.SchedulePage;

public class HomePage extends AbstractPage {

	@Inject
	private CityService cityService;

	private String selectedCityStart;
	private String selectedCityFinish;
	private Date selectedDateStart;
	private Date selectedDateFinish;

	public HomePage() {
		super();
	}

	protected void onInitialize() {
		super.onInitialize();


		List<String> cityList = new ArrayList<String>();

		for (City city : new ArrayList<City>(cityService.getAll())) {
			cityList.add(city.getName());
		}

		DropDownChoice<String> listSitesStart = new DropDownChoice<String>("sitesStart",
				new PropertyModel<String>(this, "selectedCityStart"), cityList);
		//listSitesStart.setRequired(true);

		DropDownChoice<String> listSitesFinish = new DropDownChoice<String>("sitesFinish",
				new PropertyModel<String>(this, "selectedCityFinish"), cityList);
		//listSitesFinish.setRequired(true);
		
		 DateTextField dateStartField = new DateTextField("dateStartField", new PropertyModel<Date>(
		            this, "selectedDateStart"), "dd-MM-yyyy");
		 dateStartField.add(new DatePicker());

		 DateTextField dateFinishField = new DateTextField("dateFinishField", new PropertyModel<Date>(
				 this, "selectedDateFinish"), "dd-MM-yyyy");
		 dateFinishField.add(new DatePicker());

		Form<?> form = new Form<Void>("form") {
			@Override
			protected void onSubmit() {

				setResponsePage(new SchedulePage(selectedCityStart, selectedCityFinish, selectedDateStart, selectedDateFinish));
			}
		};

		add(form);
		form.add(listSitesStart);
		form.add(listSitesFinish);
		form.add(dateStartField);
		form.add(dateFinishField);

	}

}
