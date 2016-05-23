package by.bogdevich.training.airline.webapp.page.admin.modelplane;

import javax.inject.Inject;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.modelplane.panel.ModelPlaneListPanel;
import by.bogdevich.training.airline.webapp.page.admin.plane.panel.PlaneListPanel;
import by.bogdevich.training.airline.webapp.page.admin.price.panel.PriceListPanel;

public class ModelPlanePage extends AbstractPage {

	public ModelPlanePage() {
		super();
		add(new ModelPlaneListPanel("model-plane-panel"));

	}

}
