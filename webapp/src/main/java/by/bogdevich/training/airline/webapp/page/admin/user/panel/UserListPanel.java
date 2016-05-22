package by.bogdevich.training.airline.webapp.page.admin.user.panel;

import java.io.Serializable;
import java.time.LocalDateTime;
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
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserProfile_;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.admin.price.PricesPage;
import by.bogdevich.training.airline.webapp.page.admin.user.UserEditPage;

public class UserListPanel extends Panel {

	@Inject
	private UserProfileService userProfileService;

	public UserListPanel(String id) {
		super(id);

		UserProfileDataProvider userProfileDataProvider = new UserProfileDataProvider();

		DataView<UserProfile> dataView = new DataView<UserProfile>("rows", userProfileDataProvider, 5) {
			@Override
			protected void populateItem(Item<UserProfile> item) {
				UserProfile userProfile = item.getModelObject();

				item.add(new Label("id", userProfile.getId()));
				item.add(new Label("login", userProfile.getLogin()));
				item.add(new Label("password", userProfile.getPassword()));
				item.add(new Label("first-name", userProfile.getFirstName()));
				item.add(new Label("last-name", userProfile.getLastName()));
				item.add(new Label("e-mail", userProfile.getEmail()));
				item.add(new Label("passport-number", userProfile.getPassportNumber()));
				item.add(new Label("phone-number", userProfile.getPhoneNumber()));
				item.add(new Label("cout-oder", userProfile.getCountOder()));
				
				CheckBox checkboxVip = new CheckBox("VIP", Model.of(userProfile.getVip()));
				checkboxVip.setEnabled(false);
				item.add(checkboxVip);
				
				FormatStyle dateStyle = FormatStyle.SHORT;
				DateTimeFormatter formater = DateTimeFormatter.ofLocalizedDate(dateStyle);
			//	String dateRegistr = userProfile.getDateRegistr().format(formater);
				item.add(new Label("date-registr", userProfile.getDateRegistr()));
				
				item.add(new Label("role", userProfile.getRole()));
				CheckBox checkboxRegistr = new CheckBox("active", Model.of(userProfile.getAceptRegistr()));
				checkboxRegistr.setEnabled(false);
				item.add(checkboxRegistr);
				
				
			     item.add(new Link<Void>("edit-user") {
	                    @Override
	                    public void onClick() {
	                        setResponsePage(new UserEditPage(userProfile));
	                    }
	                });
               
				
				}
		};

		add(dataView);
		add(new PagingNavigator("paging", dataView));

		 add(new OrderByBorder("sort-id", UserProfile_.id, userProfileDataProvider));
		 add(new OrderByBorder("sort-login", UserProfile_.login, userProfileDataProvider));
		 add(new OrderByBorder("sort-pssword", UserProfile_.password, userProfileDataProvider));
		 add(new OrderByBorder("sort-first-name", UserProfile_.firstName, userProfileDataProvider));
		 add(new OrderByBorder("sort-last-name", UserProfile_.lastName, userProfileDataProvider));
		 add(new OrderByBorder("sort-email", UserProfile_.email, userProfileDataProvider));
		 add(new OrderByBorder("sort-passport-number", UserProfile_.passportNumber, userProfileDataProvider));
		 add(new OrderByBorder("sort-phone-number", UserProfile_.phoneNumber, userProfileDataProvider));
		 add(new OrderByBorder("sort-count-oder", UserProfile_.countOder, userProfileDataProvider));
		 add(new OrderByBorder("sort-vip", UserProfile_.vip, userProfileDataProvider));
		 add(new OrderByBorder("sort-date-registr", UserProfile_.dateRegistr, userProfileDataProvider));
		 add(new OrderByBorder("sort-role", UserProfile_.role, userProfileDataProvider));
		 add(new OrderByBorder("sort-accept-registr", UserProfile_.aceptRegistr, userProfileDataProvider));

 	}

	private class UserProfileDataProvider extends SortableDataProvider<UserProfile, Serializable> {

		private UserProfileFilter userProfileFilter;

		public UserProfileDataProvider() {
			super();
			Injector.get().inject(this);

			userProfileFilter = new UserProfileFilter();
			setSort((Serializable) UserProfile_.login, SortOrder.ASCENDING);
		}

		@Override
		public Iterator<UserProfile> iterator(long first, long count) {
			Serializable property = getSort().getProperty();
			SortOrder propertySortOrder = getSortState().getPropertySortOrder(property);

			userProfileFilter.setSortProperty((SingularAttribute) property);
			userProfileFilter.setSortOrder(propertySortOrder.equals(SortOrder.ASCENDING) ? true : false);

			userProfileFilter.setLimit((int) count);
			userProfileFilter.setOffset((int) first);
			return userProfileService.getRecordsSorted(userProfileFilter).iterator();
		}

		@Override
		public long size() {
			return userProfileService.count(userProfileFilter);
		}

		@Override
		public IModel<UserProfile> model(UserProfile object) {
			return new Model((Serializable) object);
		}

	}

}
