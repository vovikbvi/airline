package by.bogdevich.training.airline.webapp.page.search;

import java.util.Date;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.search.panel.SearchFlightListPanel;

public class SearchPage extends AbstractPage {

	private String selectedCityStart;
	private String selectedCityFinish;
	private Date selectedDateStart;
	private Date selectedDateFinish;

	
	public SearchPage() {
		super();
	}

	


	public SearchPage(String selectedCityStart, String selectedCityFinish, Date selectedDateStart,
			Date selectedDateFinish) {
		super();
		this.selectedCityStart = selectedCityStart;
		this.selectedCityFinish = selectedCityFinish;
		this.selectedDateStart = selectedDateStart;
		this.selectedDateFinish = selectedDateFinish;
	}




	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new SearchFlightListPanel("search-flight", selectedCityStart, selectedCityFinish , selectedDateStart, selectedDateFinish));
	}
}
