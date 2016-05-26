package by.bogdevich.training.airline.webapp.page.home;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;
import by.bogdevich.training.airline.webapp.page.registr.RegistrPage;
import by.bogdevich.training.airline.webapp.page.search.SearchPage;



public class HomePage extends AbstractPage {

    public HomePage() {
        super();
        add(new Link("linkregistr") {
            @Override
            public void onClick() {
                setResponsePage(new LoginPage());
            }
        });

        add(new Link("linksearch") {
        	@Override
        	public void onClick() {
        		setResponsePage(new SearchPage());
        	}
        });
    }

}
