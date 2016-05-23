package by.bogdevich.training.airline.webapp.page.admin.plane;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.plane.panel.PlaneListPanel;
import by.bogdevich.training.airline.webapp.page.admin.price.panel.PriceListPanel;

public class PlanePage extends AbstractPage {

	public PlanePage() {
		super();
		add(new PlaneListPanel("plane-panel"));

	}

}
