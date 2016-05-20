package by.bogdevich.training.airline.webapp.page.admin.ticket;

import javax.inject.Inject;

import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;



public class TicketEditPage extends AbstractPage {
	
	@Inject
	UserProfileService userProfileService;

    public TicketEditPage() {
        super();
    
              
    }

}
