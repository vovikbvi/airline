package by.bogdevich.training.airline.webapp.page.admin.manufactured;


import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.panel.ManufacturedListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class ManufacturedPage extends AbstractPage {

	public ManufacturedPage() {
		super();
		add(new ManufacturedListPanel("manufactured-plane-panel"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new ManufacturedEditPage(new ManufacturedPlane()));
	           }
	       });

	}

}
