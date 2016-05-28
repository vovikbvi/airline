package by.bogdevich.training.airline.webapp.page.admin.plane;


import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.plane.panel.PlaneListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class PlanePage extends AbstractPage {

	public PlanePage() {
		super();
		add(new PlaneListPanel("plane-panel"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new PlaneEditPage(new Plane()));
	           }
	       });

	}

}
