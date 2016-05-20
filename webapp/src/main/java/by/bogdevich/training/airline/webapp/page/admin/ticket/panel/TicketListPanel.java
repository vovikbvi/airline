package by.bogdevich.training.airline.webapp.page.admin.ticket.panel;

import java.io.Serializable;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.Ticket_;
import by.bogdevich.training.airline.service.TicketService;

public class TicketListPanel extends Panel {

	@Inject
	private TicketService ticketService;

	public TicketListPanel(String id) {
		super(id);

		TicketDataProvider ticketDataProvider = new TicketDataProvider();

		DataView<Ticket> dataView = new DataView<Ticket>("rows", ticketDataProvider, 5) {
			@Override
			protected void populateItem(Item<Ticket> item) {
				Ticket ticket = item.getModelObject();

				item.add(new Label("id", ticket.getId()));
				item.add(new Label("flieght", ticket.getFlight().getId()));
				item.add(new Label("passanger", ticket.getUserProfile().getFirstName()));

				CheckBox checkboxPaid = new CheckBox("paid", Model.of(ticket.getPaid()));
				checkboxPaid.setEnabled(false);
				item.add(checkboxPaid);
				item.add(new Label("number-seats", ticket.getNumberSeats()));

				FormatStyle dateStyle = FormatStyle.SHORT;
				DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDate(dateStyle);
				String dateBought = ticket.getDateBought().format(formater);
				item.add(new Label("date-bought", dateBought));
				item.add(new Label("baggage", ticket.getBaggage()));
				item.add(new Label("weight-baggage", ticket.getWeightBaggage()));
				item.add(new Label("ticket-tupe", ticket.getTicketTupe()));
				item.add(new Label("ticket-class", ticket.getTicketClass()));

				CheckBox checkboxPriotityRgistr = new CheckBox("priotity-rgistr",
						Model.of(ticket.getPriorityRegistration()));
				checkboxPriotityRgistr.setEnabled(false);
				item.add(checkboxPriotityRgistr);

				CheckBox checkboxPriotitySeats = new CheckBox("priotity-seats",
						Model.of(ticket.getPriorityRegistration()));
				checkboxPriotitySeats.setEnabled(false);
				item.add(checkboxPriotitySeats);

				item.add(new Label("ticket-costs", ticket.getCosts()));

				CheckBox checkboxForBaby = new CheckBox("for-baby", Model.of(ticket.getForBaby()));
				checkboxForBaby.setEnabled(false);
				item.add(checkboxForBaby);

			}
		};

		add(dataView);
		add(new PagingNavigator("paging", dataView));

		add(new OrderByBorder("sort-id-ticket", Ticket_.id, ticketDataProvider));
		add(new OrderByBorder("sort-flieght", Ticket_.flight, ticketDataProvider));
		add(new OrderByBorder("sort-passanger", Ticket_.userProfile, ticketDataProvider));
		add(new OrderByBorder("sort-paid", Ticket_.paid, ticketDataProvider));
		add(new OrderByBorder("sort-number-seats", Ticket_.numberSeats, ticketDataProvider));
		add(new OrderByBorder("sort-date-bought", Ticket_.dateBought, ticketDataProvider));
		add(new OrderByBorder("sort-baggage", Ticket_.baggage, ticketDataProvider));
		add(new OrderByBorder("sort-weight-baggage", Ticket_.weightBaggage, ticketDataProvider));
		add(new OrderByBorder("sort-ticket-tupe", Ticket_.ticketTupe, ticketDataProvider));
		add(new OrderByBorder("sort-ticket-class", Ticket_.ticketClass, ticketDataProvider));
		add(new OrderByBorder("sort-priotity-rgistr", Ticket_.priorityRegistration, ticketDataProvider));
		add(new OrderByBorder("sort-priotity-seats", Ticket_.prioritySeats, ticketDataProvider));
		add(new OrderByBorder("sort-ticket-costs", Ticket_.costs, ticketDataProvider));
		add(new OrderByBorder("sort-for-baby", Ticket_.forBaby, ticketDataProvider));
	}

	private class TicketDataProvider extends SortableDataProvider<Ticket, Serializable> {

		private TicketFilter ticketFilter;

		public TicketDataProvider() {
			super();
			Injector.get().inject(this);

			ticketFilter = new TicketFilter();
			setSort((Serializable) Ticket_.dateBought, SortOrder.ASCENDING);

		}

		@Override
		public Iterator<Ticket> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			ticketFilter.setSortProperty((SingularAttribute) property);
			ticketFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			ticketFilter.setLimit((int) count);
			ticketFilter.setOffset((int) first);

			ticketFilter.setSetFetchUser(true);
			ticketFilter.setSetFetchFlieght(true);

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