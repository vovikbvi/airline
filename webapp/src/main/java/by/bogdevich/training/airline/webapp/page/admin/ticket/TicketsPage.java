package by.bogdevich.training.airline.webapp.page.admin.ticket;


import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.panel.TicketListPanel;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;


@AuthorizeInstantiation(value = {"ADMIN"})
public class TicketsPage extends AbstractPage {
	
    public TicketsPage() {
        super();
       add(new TicketListPanel("ticket-list-panel")); 
          
       add(new Link("create") {
           @Override
           public void onClick() {
               setResponsePage(new TicketEditPage(new Ticket()));
           }
       });

    }

}
