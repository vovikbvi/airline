package by.bogdevich.training.airline.webapp.page.myoders;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.panel.TicketListPanel;
import by.bogdevich.training.airline.webapp.page.myoders.panel.MyOdersPanel;



public class MyOdersPage extends AbstractPage {

    public MyOdersPage() {
        super();
        
        
    }
     @Override
    protected void onInitialize() {
    	super.onInitialize();
    	
    	add(new MyOdersPanel("my-oders-panel"));
    }
    
}
