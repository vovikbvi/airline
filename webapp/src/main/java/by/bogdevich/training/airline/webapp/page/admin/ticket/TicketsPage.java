package by.bogdevich.training.airline.webapp.page.admin.ticket;


import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.panel.TicketListPanel;



public class TicketsPage extends AbstractPage {
	
    public TicketsPage() {
        super();
       add(new TicketListPanel("ticket-list-panel")); 
              
    }

}
