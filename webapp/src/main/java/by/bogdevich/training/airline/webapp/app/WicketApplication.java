package by.bogdevich.training.airline.webapp.app;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import org.apache.wicket.authroles.authentication.AbstractAuthenticatedWebSession;
import org.apache.wicket.authroles.authentication.AuthenticatedWebApplication;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AnnotationsRoleAuthorizationStrategy;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.page.home.HomePage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;


@Component("wicketWebApplicationBean")
public class WicketApplication extends AuthenticatedWebApplication {
    @Inject
    private ApplicationContext applicationContext;

    @Inject
    private TicketService ticketService; 
 
    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();
        getMarkupSettings().setStripWicketTags(true);
        // add your configuration here
        
        getComponentInstantiationListeners().add(new SpringComponentInjector(this, getApplicationContext()));

        getSecuritySettings().setAuthorizationStrategy(new AnnotationsRoleAuthorizationStrategy(this));

        
        TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("delete don't paid ticket greater 3 day");
				ticketService.deleteDontPaidTicket();
			}
		}; 
        Timer timer = new Timer();
        timer.schedule(task, 60000, 8640000);
    }
   
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    @Override
    protected Class<? extends AbstractAuthenticatedWebSession> getWebSessionClass() {
        return AuthorizedSession.class;
    }

    @Override
    protected Class<? extends WebPage> getSignInPageClass() {
        return LoginPage.class;
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }
    
    

}