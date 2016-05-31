package by.bogdevich.training.airline.webapp.page.search;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.search.panel.SearchFlightListPanel;

public class SearchPage extends AbstractPage {

	private String selectedCityStart;
	private String selectedCityFinish;

	
	public SearchPage() {
		super();
	}

	
	public SearchPage(String selectedCityStart, String selectedCityFinish) {
		super();
		this.selectedCityStart = selectedCityStart;
		this.selectedCityFinish = selectedCityFinish;
	}


	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new SearchFlightListPanel("search-flight", selectedCityStart, selectedCityFinish));
	}
}
