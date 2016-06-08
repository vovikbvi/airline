package by.bogdevich.training.airline.webapp.page.admin.country.panel;

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
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import by.bogdevich.training.airline.dataaccess.filtres.CountryFilter;
import by.bogdevich.training.airline.dataaccess.filtres.FlightCatalogFilter;
import by.bogdevich.training.airline.dataaccess.filtres.FlightFilter;
import by.bogdevich.training.airline.datamodel.Airport_;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.Country_;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.FlightCatalog_;
import by.bogdevich.training.airline.datamodel.Flight_;
import by.bogdevich.training.airline.service.CountryService;
import by.bogdevich.training.airline.service.FlightCatalogService;
import by.bogdevich.training.airline.service.FlightService;
import by.bogdevich.training.airline.webapp.page.admin.country.CountryEditPage;
import by.bogdevich.training.airline.webapp.page.admin.country.CountryPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;

public class CountryListPanel extends Panel {

	@Inject
	private CountryService countryService;

	public CountryListPanel(String id) {
		super(id);

		countryDataProvider countryDataProvider = new countryDataProvider();

		DataView<Country> dataView = new DataView<Country>("rows", countryDataProvider, 5) {
			@Override
			protected void populateItem(Item<Country> item) {
				Country country = item.getModelObject();

				item.add(new Label("id", country.getId()));
				item.add(new Label("country", country.getName()));

				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							countryService.delete(country.getId());
						} catch (PersistenceException e) {
							warn("Impossible delete this record");
						}

						setResponsePage(new CountryPage());
					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new CountryEditPage(country));
					}
				});

				}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		
		 add(new OrderByBorder("sort-id", Country_.id, countryDataProvider));
		 add(new OrderByBorder("sort-country", Country_.name, countryDataProvider));
	
 	}

	private class countryDataProvider extends SortableDataProvider<Country, Serializable> {

		private CountryFilter countryFilter;

		public countryDataProvider() {
			super();
			
			countryFilter = new CountryFilter();
			setSort((Serializable) Flight_.id, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Country> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			countryFilter.setSortProperty((SingularAttribute) property);
			countryFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			countryFilter.setLimit((int) count);
			countryFilter.setOffset((int) first);
			

			return countryService.getRecordsSorted(countryFilter).iterator();
		}

		@Override
		public long size() {
			return countryService.count(countryFilter);
		}

		@Override
		public IModel<Country> model(Country object) {
			return new Model((Serializable) object);
		}

	}

}
