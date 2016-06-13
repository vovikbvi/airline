package by.bogdevich.training.airline.webapp.page.admin.airport.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.dataaccess.filtres.AirportFilter;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.service.AirportService;
import by.bogdevich.training.airline.webapp.page.admin.airport.AirportEditPage;
import by.bogdevich.training.airline.webapp.page.admin.airport.AirportPage;
import by.bogdevich.training.airline.webapp.page.admin.city.CityPage;
import by.bogdevich.training.airline.webapp.page.message.ErrorDelete;

public class AirportListPanel extends Panel {

	@Inject
	private AirportService airportService;

	public AirportListPanel(String id) {
		super(id);

		airportDataProvider airportDataProvider = new airportDataProvider();

		DataView<Airport> dataView = new DataView<Airport>("rows", airportDataProvider, 10) {
			@Override
			protected void populateItem(Item<Airport> item) {
				Airport airport = item.getModelObject();

				item.add(new Label("id", airport.getId()));
				item.add(new Label("name", airport.getName()));
				item.add(new Label("code-iata", airport.getCodeIata()));
				item.add(new Label("code-icao", airport.getCodeIcao()));
				item.add(new Label("city", airport.getCity().getName()));
				item.add(new Label("class-weight", airport.getClassWeight()));
				item.add(new Label("coordinatesX", airport.getCoordinatesX()));
				item.add(new Label("coordinatesY", airport.getCoordinatesY()));
				
				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							airportService.delete(airport.getId());
							setResponsePage(new AirportPage());
						} catch (PersistenceException e) {
							warn("Impossible delete this record");
							setResponsePage(new ErrorDelete());
						}

					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new AirportEditPage(airport));
					}
				});

				}
		};
	
		add(dataView);
		add(new PagingNavigator("paging", dataView));
		add(new FeedbackPanel("feedback"));
		
		 add(new OrderByBorder("sort-id", Airport_.id, airportDataProvider));
		 add(new OrderByBorder("sort-name", Airport_.name, airportDataProvider));
		 add(new OrderByBorder("sort-codeIata", Airport_.codeIata, airportDataProvider));
		 add(new OrderByBorder("sort-codeIcao", Airport_.codeIcao, airportDataProvider));
		 add(new OrderByBorder("sort-city", City_.name, airportDataProvider));
		 add(new OrderByBorder("sort-class-weight", Airport_.classWeight, airportDataProvider));
		 add(new OrderByBorder("sort-coordinatesX", Airport_.coordinatesX, airportDataProvider));
		 add(new OrderByBorder("sort-coordinatesY", Airport_.coordinatesY, airportDataProvider));
	
 	}

	private class airportDataProvider extends SortableDataProvider<Airport, Serializable> {

		private AirportFilter airportFilter;

		public airportDataProvider() {
			super();
			
			airportFilter = new AirportFilter();
			setSort((Serializable) Flight_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Airport> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			airportFilter.setSortProperty((SingularAttribute) property);
			airportFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			airportFilter.setLimit((int) count);
			airportFilter.setOffset((int) first);
			
			airportFilter.setFetchCity(true);;
			
			return airportService.getRecordsSorted(airportFilter).iterator();
		}

		@Override
		public long size() {
			return airportService.count(airportFilter);
		}

		@Override
		public IModel<Airport> model(Airport object) {
			return new Model((Serializable) object);
		}

	}

}
