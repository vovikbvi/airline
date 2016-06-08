package by.bogdevich.training.airline.webapp.page.admin.manufactured.panel;

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

import by.bogdevich.training.airline.dataaccess.filtres.ManufacturedPlaneFilter;
import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane_;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Plane_;
import by.bogdevich.training.airline.service.ManufacturedPlainService;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.ManufacturedEditPage;
import by.bogdevich.training.airline.webapp.page.admin.manufactured.ManufacturedPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;

public class ManufacturedListPanel extends Panel {

	@Inject
	private ManufacturedPlainService manufacturedPlainService;

	public ManufacturedListPanel(String id) {
		super(id);

		manufacturedPlaneDataProvider manufacturedPlaneDataProvider = new manufacturedPlaneDataProvider();

		DataView<ManufacturedPlane> dataView = new DataView<ManufacturedPlane>("rows", manufacturedPlaneDataProvider, 5) {
			@Override
			protected void populateItem(Item<ManufacturedPlane> item) {
				ManufacturedPlane manufacturedPlane = item.getModelObject();

				item.add(new Label("id", manufacturedPlane.getId()));
				item.add(new Label("manufactured-name", manufacturedPlane.getName()));
				
				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							manufacturedPlainService.delete(manufacturedPlane.getId());
						} catch (PersistenceException e) {
							warn("Impossible delete this record");
						}

						setResponsePage(new ManufacturedPage());
					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new ManufacturedEditPage(manufacturedPlane));
					}
				});

				}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		 add(new OrderByBorder("sort-id", ManufacturedPlane_.id, manufacturedPlaneDataProvider));
		 add(new OrderByBorder("sort-name", ManufacturedPlane_.name, manufacturedPlaneDataProvider));

 	}

	private class manufacturedPlaneDataProvider extends SortableDataProvider<ManufacturedPlane, Serializable> {

		private ManufacturedPlaneFilter manufacturedPlaneFilter;

		public manufacturedPlaneDataProvider() {
			super();
			
			manufacturedPlaneFilter = new ManufacturedPlaneFilter();
			setSort((Serializable) ManufacturedPlane_.name, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<ManufacturedPlane> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			manufacturedPlaneFilter.setSortProperty((SingularAttribute) property);
			manufacturedPlaneFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			manufacturedPlaneFilter.setLimit((int) count);
			manufacturedPlaneFilter.setOffset((int) first);

			return manufacturedPlainService.getRecordsSorted(manufacturedPlaneFilter).iterator();
		}

		@Override
		public long size() {
			return manufacturedPlainService.count(manufacturedPlaneFilter);
		}

		@Override
		public IModel<ManufacturedPlane> model(ManufacturedPlane object) {
			return new Model((Serializable) object);
		}

	}

}
