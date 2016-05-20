package by.bogdevich.training.airline.webapp.page.setting;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.price.PricesPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;
import by.bogdevich.training.airline.webapp.page.registr.RegistrPage;

public class SettingPage extends AbstractPage {

	public SettingPage() {
		super();
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
        add(new Link("users-table") {
            @Override
            public void onClick() {
                setResponsePage(new UsersPage());
            }
        });

        add(new Link("tickets-table") {
            @Override
            public void onClick() {
                setResponsePage(new TicketsPage());
            }
        });

        add(new Link("prices-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new PricesPage());
        	}
        });
	
	}
}
