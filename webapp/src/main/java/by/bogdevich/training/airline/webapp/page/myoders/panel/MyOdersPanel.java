package by.bogdevich.training.airline.webapp.page.myoders.panel;

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
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.Ticket_;
import by.bogdevich.training.airline.datamodel.UserProfile_;
import by.bogdevich.training.airline.service.TicketService;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.page.bookticket.BookTicketPage;
import by.bogdevich.training.airline.webapp.page.info.TicketInfoPage;
import by.bogdevich.training.airline.webapp.page.payment.PaymentPage;

public class MyOdersPanel extends Panel {

	@Inject
	private TicketService ticketService;

	public MyOdersPanel(String id) {
		super(id);

		MyOdersProvider myOdersProvider = new MyOdersProvider();

		DataView<Ticket> dataView = new DataView<Ticket>("rows", myOdersProvider, 10) {
			@Override
			protected void populateItem(Item<Ticket> item) {
				Ticket ticket = item.getModelObject();

				item.add(new Label("id", ticket.getId()));
				item.add(new Label("airport-start", ticket.getFlight().getFlightCatalog().getAirportStart().getName()));
				item.add(new Label("airport-finish", ticket.getFlight().getFlightCatalog().getAirportFinish().getName()));

				item.add(DateLabel.forDatePattern("date-bought", Model.of(ticket.getDateBought()), "dd-MM-yyyy"));
				
    			item.add(new Label("ticket-class", ticket.getTicketClass()));
				item.add(new Label("ticket-costs", ticket.getCosts()));

				CheckBox checkboxPaid = new CheckBox("paid", Model.of(ticket.getPaid()));
				checkboxPaid.setEnabled(false);
				item.add(checkboxPaid);
				
				Link paidLink = new Link<Void>("paid-link") {
					@Override
					public void onClick() {
						setResponsePage(new PaymentPage(ticket));
					}
				};
				item.add(paidLink);
				paidLink.setVisible(!ticket.getPaid());
				
				
				item.add(new Link<Void>("detail-link") {
					@Override
					public void onClick() {
						setResponsePage(new TicketInfoPage(ticket));
					}
				});
			}
		};

		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id-ticket", Ticket_.id, myOdersProvider));
		add(new OrderByBorder("sort-date-bought", Ticket_.dateBought, myOdersProvider));
	}

	private class MyOdersProvider extends SortableDataProvider<Ticket, Serializable> {

		private TicketFilter ticketFilter;

		public MyOdersProvider() {
			super();
			Injector.get().inject(this);

			ticketFilter = new TicketFilter();
			setSort((Serializable) Ticket_.dateBought, SortOrder.DESCENDING);

		}

		@Override
		public Iterator<Ticket> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			ticketFilter.setSortProperty((SingularAttribute) property);
			ticketFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			ticketFilter.setLimit((int) count);
			ticketFilter.setOffset((int) first);

			ticketFilter.setFetchUser(true);
			ticketFilter.setFetchAirport(true);
			
			ticketFilter.setUser(AuthorizedSession.get().getLoggedUser());

			return ticketService.getRecordsSorted(ticketFilter).iterator();
		}

		@Override
		public long size() {
			return ticketService.count(ticketFilter);
		}

		@Override
		public IModel<Ticket> model(Ticket object) {
			return new Model((Serializable) object);
		}

	}

}
