package by.bogdevich.training.airline.webapp.page.admin.user;

import javax.inject.Inject;

import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;



public class UserEditPage extends AbstractPage {
	
	@Inject
	UserProfileService userProfileService;

    public UserEditPage() {
        super();
    
              
    }

}
