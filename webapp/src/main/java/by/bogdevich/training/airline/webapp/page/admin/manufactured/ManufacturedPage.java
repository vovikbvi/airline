package by.bogdevich.training.airline.webapp.page.admin.manufactured;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;
import by.bogdevich.training.airline.webapp.page.admin.plane.panel.PlaneListPanel;

public class ManufacturedPage extends AbstractPage {

	public ManufacturedPage() {
		super();
		add(new ManufacturedListPanel("manufactured-plane-panel"));

	}

}
