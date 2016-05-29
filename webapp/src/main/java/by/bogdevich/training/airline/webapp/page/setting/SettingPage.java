package by.bogdevich.training.airline.webapp.page.setting;

import org.apache.wicket.markup.html.link.Link;

import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.admin.airport.AirportPage;
import by.bogdevich.training.airline.webapp.page.admin.city.CityPage;
import by.bogdevich.training.airline.webapp.page.admin.country.CountryPage;
import by.bogdevich.training.airline.webapp.page.admin.flight.FlightPage;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.FlightCatalogPage;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.ManufacturedPage;
import by.bogdevich.training.airline.webapp.page.admin.modelplane.ModelPlanePage;
import by.bogdevich.training.airline.webapp.page.admin.plane.PlanePage;
import by.bogdevich.training.airline.webapp.page.admin.price.PricesPage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;

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

        add(new Link("plane-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new PlanePage());
        	}
        });
      
        add(new Link("model-plane-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new ModelPlanePage());
        	}
        });
        
        add(new Link("manufactured-plane-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new ManufacturedPage());
        	}
        });
        
        add(new Link("flight-catalog-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new FlightCatalogPage());
        	}
        });
        
        add(new Link("flight-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new FlightPage());
        	}
        });

        add(new Link("country-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new CountryPage());
        	}
        });
        
        add(new Link("city-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new CityPage());
        	}
        });
        
        add(new Link("airport-table") {
        	@Override
        	public void onClick() {
        		setResponsePage(new AirportPage());
        	}
        });
        
 
	}
}
