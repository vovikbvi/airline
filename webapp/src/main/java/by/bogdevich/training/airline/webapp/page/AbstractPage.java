package by.bogdevich.training.airline.webapp.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.component.menu.MenuUser;
import by.bogdevich.training.airline.webapp.page.home.HomePage;



public abstract class AbstractPage extends WebPage {
	
	

	public AbstractPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
		add(new MenuUser("menu-user"));
		
        add(new Link("home-page") {
            @Override
            public void onClick() {
                setResponsePage(new HomePage());
            }
        });

	}
}
