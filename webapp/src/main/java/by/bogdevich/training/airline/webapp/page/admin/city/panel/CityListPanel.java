package by.bogdevich.training.airline.webapp.page.admin.city.panel;

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
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.dataaccess.filtres.CityFilter;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.City_;
import by.bogdevich.training.airline.datamodel.Country_;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.service.CityService;
import by.bogdevich.training.airline.webapp.page.admin.city.CityEditPage;
import by.bogdevich.training.airline.webapp.page.admin.city.CityPage;

public class CityListPanel extends Panel {

	@Inject
	private CityService cityService;

	public CityListPanel(String id) {
		super(id);

		cityDataProvider cityDataProvider = new cityDataProvider();

		DataView<City> dataView = new DataView<City>("rows", cityDataProvider, 5) {
			@Override
			protected void populateItem(Item<City> item) {
				City city = item.getModelObject();

				item.add(new Label("id", city.getId()));
				item.add(new Label("city", city.getName()));
				item.add(new Label("country", city.getCountry().getName()));
				
				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							cityService.delete(city.getId());
						} catch (PersistenceException e) {
							warn("Impossible delete this record");
						}

						setResponsePage(new CityPage());
					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new CityEditPage(city));
					}
				});

				}
		};
	
		add(dataView);
		add(new PagingNavigator("paging", dataView));
		
		 add(new OrderByBorder("sort-id", City_.id, cityDataProvider));
		 add(new OrderByBorder("sort-city", City_.name, cityDataProvider));
		 add(new OrderByBorder("sort-country", Country_.name, cityDataProvider));
	
 	}

	private class cityDataProvider extends SortableDataProvider<City, Serializable> {

		private CityFilter cityFilter;

		public cityDataProvider() {
			super();
			
			cityFilter = new CityFilter();
			setSort((Serializable) Flight_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<City> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			cityFilter.setSortProperty((SingularAttribute) property);
			cityFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			cityFilter.setLimit((int) count);
			cityFilter.setOffset((int) first);
			
			cityFilter.setFetchCountry(true);
			
			return cityService.getRecordsSorted(cityFilter).iterator();
		}

		@Override
		public long size() {
			return cityService.count(cityFilter);
		}

		@Override
		public IModel<City> model(City object) {
			return new Model((Serializable) object);
		}

	}

}
