package by.bogdevich.training.airline.webapp.page.registr;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeInstantiation;
import org.apache.wicket.extensions.markup.html.form.DateTextField;
import org.apache.wicket.extensions.yui.calendar.DatePicker;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.service.SemdMail;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.common.renderer.UserRoleChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.home.HomePage;

public class RegistrationPage extends AbstractPage {

	@Inject
	private UserProfileService userProfileService;

	private UserProfile userProfile;
	
	@Inject
	private SemdMail semdMail;

	
	public RegistrationPage() {
		super();
	}
	
	
	public RegistrationPage(UserProfile userProfile) {
		super();
		this.userProfile = userProfile;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();
		
	
			String stringRegistration = getString("ui.user_registr");
			String stringEditProfile = String.format("%s - %s",getString("ui.user_edit"), userProfile.getLogin());
			//String stringEditProfile = String.format("Edit my proifile -"+ userProfile.getLogin());
			
			String stringHeader = (userProfile.getId() == null) ? stringRegistration : stringEditProfile;
			add(new Label("header", stringHeader));

		
		Form form = new Form("form", new CompoundPropertyModel<UserProfile>(userProfile));
		add(form);
		
		TextField<String> loginField = new TextField<>("login");
		loginField.setEnabled(userProfile.getId() == null);
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
		countOderField.setVisible(userProfile.getId() != null);
		countOderField.setVisible(false);
		form.add(countOderField);

	
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
			try{
				if (userProfile.getId() == null) {
					
					
					userProfile.setCountOder(0);
					userProfileService.registration(userProfile);
				} else {
					userProfileService.update(userProfile);
				}
				
				setResponsePage(new HomePage());
			}catch (Exception e){
				error("User exist");
				}
			}
			
		});

		add(new FeedbackPanel("feedback"));

	    
	}


}
