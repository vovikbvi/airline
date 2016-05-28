package by.bogdevich.training.airline.webapp.page.admin.modelplane;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.modelplane.panel.ModelPlaneListPanel;
import by.bogdevich.training.airline.webapp.page.admin.plane.PlaneEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class ModelPlanePage extends AbstractPage {

	public ModelPlanePage() {
		super();
		add(new ModelPlaneListPanel("model-plane-panel"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new ModelPlaneEditPage(new ModelPlane()));
	           }
	       });

	}

}
