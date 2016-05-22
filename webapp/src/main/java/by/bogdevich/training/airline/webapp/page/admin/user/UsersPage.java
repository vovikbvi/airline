package by.bogdevich.training.airline.webapp.page.admin.user;

import javax.inject.Inject;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.user.panel.UserListPanel;



public class UsersPage extends AbstractPage {
	
	@Inject
	UserProfileService userProfileService;
	

    
    public UsersPage() {
        super();
       add(new UserListPanel("user-list-panel")); 
              
    }

 }
