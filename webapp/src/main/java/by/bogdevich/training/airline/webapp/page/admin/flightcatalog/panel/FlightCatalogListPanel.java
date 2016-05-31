package by.bogdevich.training.airline.webapp.page.admin.flightcatalog.panel;

import java.io.Serializable;
import java.util.Iterator;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.persistence.metamodel.SingularAttribute;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.OrderByBorder;
import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.dataaccess.filtres.FlightCatalogFilter;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.service.FlightCatalogService;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.FlightCatalogEditPage;
import by.bogdevich.training.airline.webapp.page.admin.flightcatalog.FlightCatalogPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;

public class FlightCatalogListPanel extends Panel {

	@Inject
	private FlightCatalogService flightCatalogService;

	public FlightCatalogListPanel(String id) {
		super(id);

		flightCatalogDataProvider flightCatalogDataProvider = new flightCatalogDataProvider();

		DataView<FlightCatalog> dataView = new DataView<FlightCatalog>("rows", flightCatalogDataProvider, 20) {
			@Override
			protected void populateItem(Item<FlightCatalog> item) {
				FlightCatalog flightCatalog = item.getModelObject();

				item.add(new Label("id", flightCatalog.getId()));
				item.add(new Label("airport-start", flightCatalog.getAirportStart().getName()));
				item.add(new Label("airport-finish", flightCatalog.getAirportFinish().getName()));
				item.add(new Label("distance", flightCatalog.getDistance()));
	
				CheckBox checkboxInternational = new CheckBox("international", Model.of(flightCatalog.getInternational()));
				checkboxInternational.setEnabled(false);
				item.add(checkboxInternational);

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							flightCatalogService.delete(flightCatalog.getId());
						} catch (PersistenceException e) {
							System.out.println("Impossible delete this record");
						}

						setResponsePage(new FlightCatalogPage());
					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new FlightCatalogEditPage(flightCatalog));
					}
				});

				
				}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		//проверить сортировку по аэропорт финиш
		
		 add(new OrderByBorder("sort-id", FlightCatalog_.id, flightCatalogDataProvider));
		 add(new OrderByBorder("sort-airport-start", Airport_.name, flightCatalogDataProvider));
		 add(new OrderByBorder("sort-airport-finish", Airport_.name, flightCatalogDataProvider));
		 add(new OrderByBorder("sort-distance", FlightCatalog_.distance, flightCatalogDataProvider));
		 add(new OrderByBorder("sort-international", FlightCatalog_.international, flightCatalogDataProvider));

 	}

	private class flightCatalogDataProvider extends SortableDataProvider<FlightCatalog, Serializable> {

		private FlightCatalogFilter flightCatalogFilter;

		public flightCatalogDataProvider() {
			super();
			
			flightCatalogFilter = new FlightCatalogFilter();
			setSort((Serializable) FlightCatalog_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<FlightCatalog> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			flightCatalogFilter.setSortProperty((SingularAttribute) property);
			flightCatalogFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			flightCatalogFilter.setLimit((int) count);
			flightCatalogFilter.setOffset((int) first);
			
			flightCatalogFilter.setFetchAirportStart(true);
			flightCatalogFilter.setFetchAirportFinish(true);
			
			//flightCatalogFilter.setCityStart("updatedName");

			return flightCatalogService.getRecordsSorted(flightCatalogFilter).iterator();
		}

		@Override
		public long size() {
			return flightCatalogService.count(flightCatalogFilter);
		}

		@Override
		public IModel<FlightCatalog> model(FlightCatalog object) {
			return new Model((Serializable) object);
		}

	}

}
