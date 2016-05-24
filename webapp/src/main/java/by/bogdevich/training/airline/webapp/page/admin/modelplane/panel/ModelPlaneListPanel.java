package by.bogdevich.training.airline.webapp.page.admin.modelplane.panel;

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

import by.bogdevich.training.airline.dataaccess.filtres.ModelPlaneFilter;
import by.bogdevich.training.airline.dataaccess.filtres.PlaneFilter;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane_;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane_;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Plane_;
import by.bogdevich.training.airline.service.ModelPlaneService;
import by.bogdevich.training.airline.service.PlaneService;
import by.bogdevich.training.airline.webapp.page.admin.modelplane.ModelPlaneEditPage;
import by.bogdevich.training.airline.webapp.page.admin.modelplane.ModelPlanePage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UsersPage;

public class ModelPlaneListPanel extends Panel {

	@Inject
	private ModelPlaneService modelPlaneService;

	public ModelPlaneListPanel(String id) {
		super(id);

		modelPlaneDataProvider modelPlaneDataProvider = new modelPlaneDataProvider();

		DataView<ModelPlane> dataView = new DataView<ModelPlane>("rows", modelPlaneDataProvider, 5) {
			@Override
			protected void populateItem(Item<ModelPlane> item) {
				ModelPlane modelPlane = item.getModelObject();

				item.add(new Label("id", modelPlane.getId()));
				item.add(new Label("manufacturedPlane", modelPlane.getManufacturedPlane().getName()));
				item.add(new Label("model", modelPlane.getModel()));
				item.add(new Label("colPassangersBuisnes", modelPlane.getColPassangersBuisnes()));
				item.add(new Label("colPassangersFirstclass", modelPlane.getColPassangersFirstclass()));
				item.add(new Label("colPassangersEconomy", modelPlane.getColPassangersEconomy()));
				item.add(new Label("weightAllBaggage", modelPlane.getWeightAllBaggage()));
				item.add(new Label("avgSpeed", modelPlane.getAvgSpeed()));
				item.add(new Label("classWeight", modelPlane.getClassWeight()));
				
				item.add(new Link<Void>("delete-link") {
					@Override
					public void onClick() {
						try {
							modelPlaneService.delete(modelPlane.getId());
						} catch (PersistenceException e) {
							System.out.println("Impossible delete this record");
						}

						setResponsePage(new ModelPlanePage());
					}
				});

				item.add(new Link<Void>("edit-link") {
					@Override
					public void onClick() {
						setResponsePage(new ModelPlaneEditPage(modelPlane));
					}
				});

						}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		 add(new OrderByBorder("sort-id", ModelPlane_.id, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-manufactured", ManufacturedPlane_.name, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-model", ModelPlane_.model, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-colPassangersBuisnes", ModelPlane_.colPassangersBuisnes, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-colPassangersFirstclass", ModelPlane_.colPassangersFirstclass, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-colPassangersEconomy", ModelPlane_.colPassangersEconomy, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-weightAllBaggage", ModelPlane_.weightAllBaggage, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-avgSpeed", ModelPlane_.avgSpeed, modelPlaneDataProvider));
		 add(new OrderByBorder("sort-classWeight", ModelPlane_.classWeight, modelPlaneDataProvider));

		 
 	}

	private class modelPlaneDataProvider extends SortableDataProvider<ModelPlane, Serializable> {

		private ModelPlaneFilter modelPlaneFilter;

		public modelPlaneDataProvider() {
			super();
			
			modelPlaneFilter = new ModelPlaneFilter();
			setSort((Serializable) ModelPlane_.model, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<ModelPlane> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			modelPlaneFilter.setSortProperty((SingularAttribute) property);
			modelPlaneFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			modelPlaneFilter.setLimit((int) count);
			modelPlaneFilter.setOffset((int) first);

			modelPlaneFilter.setSetFetchManufactured(true);
			
			return modelPlaneService.getRecordsSorted(modelPlaneFilter).iterator();
		}

		@Override
		public long size() {
			return modelPlaneService.count(modelPlaneFilter);
		}

		@Override
		public IModel<ModelPlane> model(ModelPlane object) {
			return new Model((Serializable) object);
		}

	}

}
