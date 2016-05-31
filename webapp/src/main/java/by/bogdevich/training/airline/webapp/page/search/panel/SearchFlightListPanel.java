package by.bogdevich.training.airline.webapp.page.search.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.datetime.markup.html.basic.DateLabel;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.webapp.page.admin.flight.FlightEditPage;
import by.bogdevich.training.airline.webapp.page.admin.flight.FlightPage;
import by.bogdevich.training.airline.webapp.page.ticket.BookTicketPage;

public class SearchFlightListPanel extends Panel {

	@Inject
	private FlightService flightService;
	
	private String cityStart;
	private String cityFinish;

	public SearchFlightListPanel(String id) {
		super(id);
	}

	public SearchFlightListPanel(String id, String cityStart, String cityFinish) {
		super(id);
		this.cityStart = cityStart;
		this.cityFinish = cityFinish;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		searchFlightDataProvider searchFlightDataProvider = new searchFlightDataProvider();

		DataView<Flight> dataView = new DataView<Flight>("rows", searchFlightDataProvider, 20) {
			@Override
			protected void populateItem(Item<Flight> item) {
				Flight flight = item.getModelObject();

				item.add(new Label("id", flight.getId()));
				item.add(new Label("flight-catalog", flight.getFlightCatalog().getId()));
				item.add(new Label("airport-start", flight.getFlightCatalog().getAirportStart().getName()));
				item.add(new Label("airport-finish", flight.getFlightCatalog().getAirportFinish().getName()));
				item.add(DateLabel.forDatePattern("registr-time", Model.of(flight.getRegistrTime()), "dd-MM-yyyy hh:mm"));
				item.add(DateLabel.forDatePattern("departure-time", Model.of(flight.getDepartureTime()), "dd-MM-yyyy hh:mm"));
				item.add(DateLabel.forDatePattern("arrival-time", Model.of(flight.getArrivalTime()), "dd-MM-yyyy hh:mm"));
				item.add(new Label("plane", flight.getPlane().getBortNumber()));
				item.add(DateLabel.forDatePattern("start-sale-ticket", Model.of(flight.getStartSaleTicket()), "dd-MM-yyyy"));
	
	
				item.add(new Link<Void>("booking-link") {
					@Override
					public void onClick() {
						setResponsePage(new BookTicketPage(new Ticket(), flight));
					}
				});

				}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		//проверить сортировку по plane
		
		 add(new OrderByBorder("sort-id", Flight_.id, searchFlightDataProvider));
		 add(new OrderByBorder("sort-flight-catalog", FlightCatalog_.id, searchFlightDataProvider));
		 add(new OrderByBorder("sort-registr-time", Flight_.registrTime, searchFlightDataProvider));
		 add(new OrderByBorder("sort-departure-time", Flight_.departureTime, searchFlightDataProvider));
		 add(new OrderByBorder("sort-arrival-time", Flight_.arrivalTime, searchFlightDataProvider));
		 add(new OrderByBorder("sort-plane", Flight_.plane, searchFlightDataProvider));
		 add(new OrderByBorder("sort-start-sale-ticket", Flight_.startSaleTicket, searchFlightDataProvider));
	
 	}

	private class searchFlightDataProvider extends SortableDataProvider<Flight, Serializable> {

		private FlightFilter flightFilter;

		public searchFlightDataProvider() {
			super();
			
			flightFilter = new FlightFilter();
			setSort((Serializable) Flight_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Flight> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			flightFilter.setSortProperty((SingularAttribute) property);
			flightFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			flightFilter.setLimit((int) count);
			flightFilter.setOffset((int) first);
			
			flightFilter.setFetchFlieghtCatalog(true);
			flightFilter.setFetchPlane(true);
			
			flightFilter.setCityStart(cityStart);
			flightFilter.setCityFinish(cityFinish);

			return flightService.getRecordsSorted(flightFilter).iterator();
		}

		@Override
		public long size() {
			return flightService.count(flightFilter);
		}

		@Override
		public IModel<Flight> model(Flight object) {
			return new Model((Serializable) object);
		}

	}
}
