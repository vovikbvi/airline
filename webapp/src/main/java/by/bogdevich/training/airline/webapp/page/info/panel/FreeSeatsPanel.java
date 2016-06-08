package by.bogdevich.training.airline.webapp.page.info.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.Ticket_;
import by.bogdevich.training.airline.datamodel.UserProfile_;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.page.bookticket.BookTicketPage;
import by.bogdevich.training.airline.webapp.page.payment.PaymentPage;

public class FreeSeatsPanel extends Panel {

	private Flight flight;

	@Inject
	private FlightService flightService;

	public FreeSeatsPanel(String id, Flight flight) {
		super(id);
		this.flight = flight;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		add(new Label("free-seats-buisnes", flight.getPlane().getModelPlane().getColPassangersBuisnes()-flightService.countBesySeats(flight, TicketClass.BUSINES_CLASS)));
		add(new Label("free-seats-firstclass", flight.getPlane().getModelPlane().getColPassangersFirstclass()-flightService.countBesySeats(flight, TicketClass.FIRST_CLASS)));
		add(new Label("free-seats-economy", flight.getPlane().getModelPlane().getColPassangersEconomy()-flightService.countBesySeats(flight, TicketClass.ECONOMY)));

	}

}
