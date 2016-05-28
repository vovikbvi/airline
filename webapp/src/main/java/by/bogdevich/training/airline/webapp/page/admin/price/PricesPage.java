package by.bogdevich.training.airline.webapp.page.admin.price;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.price.panel.PriceListPanel;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

@AuthorizeInstantiation(value = {"ADMIN", "OPERATOR"})
public class PricesPage extends AbstractPage {

	@Inject
	private UserProfileService userProfileService;

	public PricesPage() {
		super();
		add(new PriceListPanel("price-panel"));

	       add(new Link("create") {
	           @Override
	           public void onClick() {
	               setResponsePage(new PriceEditPage(new Price()));
	           }
	       });

	}

}
