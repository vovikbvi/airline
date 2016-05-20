package by.bogdevich.training.airline.webapp.page.admin.price;

import javax.inject.Inject;

import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.price.panel.PriceListPanel;

public class PricesPage extends AbstractPage {

	@Inject
	private UserProfileService userProfileService;

	public PricesPage() {
		super();
		add(new PriceListPanel("price-panel"));

	}

}
