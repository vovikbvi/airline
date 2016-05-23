package by.bogdevich.training.airline.webapp.page.admin.price.panel;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Iterator;

import javax.inject.Inject;
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

import by.bogdevich.training.airline.dataaccess.filtres.PriceFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Price_;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserProfile_;
import by.bogdevich.training.airline.service.PriceService;
import by.bogdevich.training.airline.webapp.page.admin.ticket.TicketsPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

public class PriceListPanel extends Panel {

	@Inject
	private PriceService priceService;

	public PriceListPanel(String id) {
		super(id);

		priceDataProvider priceDataProvider = new priceDataProvider();

		DataView<Price> dataView = new DataView<Price>("rows", priceDataProvider, 5) {
			@Override
			protected void populateItem(Item<Price> item) {
				Price price = item.getModelObject();

				item.add(new Label("id", price.getId()));
				
			//	FormatStyle dateStyle = FormatStyle.SHORT;
			//	DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDate(dateStyle);
			//	String dateRegistr = price.getDataChange().format(formater);
				item.add(DateLabel.forDatePattern("date-chane", Model.of(price.getDataChange()), "dd-MM-yyyy"));

				
				item.add(new Label("price", price.getBasicPrice()));

				}
		};

	
		add(dataView);
		add(new PagingNavigator("paging", dataView));

		 add(new OrderByBorder("sort-id", Price_.id, priceDataProvider));
		 add(new OrderByBorder("sort-date-chane", Price_.dataChange, priceDataProvider));
		 add(new OrderByBorder("sort-price", Price_.basicPrice, priceDataProvider));
 	}

	private class priceDataProvider extends SortableDataProvider<Price, Serializable> {

		private PriceFilter priceFilter;

		public priceDataProvider() {
			super();
			
			priceFilter = new PriceFilter();
			setSort((Serializable) Price_.dataChange, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<Price> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			priceFilter.setSortProperty((SingularAttribute) property);
			priceFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			priceFilter.setLimit((int) count);
			priceFilter.setOffset((int) first);
			return priceService.getRecordsSorted(priceFilter).iterator();
		}

		@Override
		public long size() {
			return priceService.count(priceFilter);
		}

		@Override
		public IModel<Price> model(Price object) {
			return new Model((Serializable) object);
		}

	}

}
