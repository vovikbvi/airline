package by.bogdevich.training.airline.webapp.page.admin.user;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.common.UserRoleChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN"})
public class UserEditPage extends AbstractPage {

	@Inject
	private UserProfileService userProfileService;

	private UserProfile userProfile;
	
	public UserEditPage() {
		super();
	}

	public UserEditPage(UserProfile userProfile) {
		super();
		this.userProfile = userProfile;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		Form form = new Form("form", new CompoundPropertyModel<UserProfile>(userProfile));
		add(form);

		TextField<String> loginField = new TextField<>("login");
		loginField.setVisible(userProfile.getId() == null);
		loginField.setRequired(true);
		form.add(loginField);
		
		TextField<String> passwordField = new TextField<>("password");
		passwordField.setVisible(userProfile.getId() == null);
		passwordField.setRequired(true);
		form.add(passwordField);
		
		TextField<String> firstNameField = new TextField<>("firstName");
		firstNameField.setRequired(true);
		form.add(firstNameField);

		TextField<String> lastNameField = new TextField<>("lastName");
		lastNameField.setRequired(true);
		form.add(lastNameField);
		
		TextField<String> emailField = new TextField<>("email");
		emailField.setRequired(true);
		form.add(emailField);
		
		TextField<String> passportNumberField = new TextField<>("passportNumber");
		passportNumberField.setRequired(true);
		form.add(passportNumberField);
		
		TextField<String> phoneNumberField = new TextField<>("phoneNumber");
		phoneNumberField.setRequired(true);
		form.add(phoneNumberField);
		
		TextField<Integer> countOderField = new TextField<>("countOder");
		//countOderField.setRequired(true);
		form.add(countOderField);
		
		CheckBox vipField = new CheckBox("vip");
		form.add(vipField);
		
		DateTextField dateRegistrField = new DateTextField("dateRegistr", "dd-MM-yyyy");
		//dateRegistrField.add(new DatePicker());
		dateRegistrField.setEnabled(false);
        dateRegistrField.setRequired(true);
		form.add(dateRegistrField);

        DropDownChoice<UserRole> roleField = new DropDownChoice<>("role", Arrays.asList(UserRole.values()), UserRoleChoiceRenderer.INSTANCE);
        roleField.setRequired(true);
        form.add(roleField);

		CheckBox aceptRegistrField = new CheckBox("aceptRegistr");
		form.add(aceptRegistrField);

	
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
				
				if (userProfile.getId() == null) {
					userProfileService.registration(userProfile);
				} else {
					userProfileService.update(userProfile);
				}
				setResponsePage(new UsersPage());
			}
		});

		add(new FeedbackPanel("feedback"));

	    
	}

}
