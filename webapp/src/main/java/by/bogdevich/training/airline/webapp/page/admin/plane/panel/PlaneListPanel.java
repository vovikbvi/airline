package by.bogdevich.training.airline.webapp.page.admin.plane.panel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
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

import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.dataaccess.filtres.PriceFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Plane_;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Price_;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserProfile_;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.webapp.page.admin.plane.PlaneEditPage;
import by.bogdevich.training.airline.webapp.page.admin.plane.PlanePage;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;

public class PlaneListPanel extends Panel {

	@Inject
	private PlaneService planeService;

	public PlaneListPanel(String id) {
		super(id);

		planeDataProvider planeDataProvider = new planeDataProvider();

		DataView<Plane> dataView = new DataView<Plane>("rows", planeDataProvider, 5) {
			@Override
			protected void populateItem(Item<Plane> item) {
				Plane plane = item.getModelObject();

				item.add(new Label("id", plane.getId()));
				item.add(new Label("modelPlane", plane.getModelPlane().getModel()));
				item.add(new Label("bortNumber", plane.getBortNumber()));
				
				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							planeService.delete(plane.getId());
						} catch (PersistenceException e) {
							warn("Impossible delete this record");
						}

						setResponsePage(new PlanePage());
					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new PlaneEditPage(plane));
					}
					
				});

				}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		 add(new OrderByBorder("sort-id", Plane_.id, planeDataProvider));
		 add(new OrderByBorder("sort-model-plane", ModelPlane_.model, planeDataProvider));
		 add(new OrderByBorder("sort-bort-number", Plane_.bortNumber, planeDataProvider));

 	}

	private class planeDataProvider extends SortableDataProvider<Plane, Serializable> {

		private PlaneFilter planeFilter;

		public planeDataProvider() {
			super();
			
			planeFilter = new PlaneFilter();
			setSort((Serializable) Plane_.bortNumber, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Plane> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			planeFilter.setSortProperty((SingularAttribute) property);
			planeFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			planeFilter.setLimit((int) count);
			planeFilter.setOffset((int) first);

			planeFilter.setFetchModelPlane(true);
			
			return planeService.getRecordsSorted(planeFilter).iterator();
		}

		@Override
		public long size() {
			return planeService.count(planeFilter);
		}

		@Override
		public IModel<Plane> model(Plane object) {
			return new Model((Serializable) object);
		}

	}

}
