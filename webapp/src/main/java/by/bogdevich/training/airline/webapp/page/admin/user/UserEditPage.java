package by.bogdevich.training.airline.webapp.page.admin.user;

import java.util.Arrays;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authroles.authorization.strategies.role.annotations.AuthorizeAction;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.common.renderer.UserRoleChoiceRenderer;
import by.bogdevich.training.airline.webapp.page.AbstractPage;

@AuthorizeInstantiation(value = {"ADMIN"})
//@AuthorizeAction(roles = {"ADMIN"}, action = Action.RENDER)
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
		countOderField.setVisible(AuthorizedSession.get().isSignedIn()&&AuthorizedSession.get().getRoles().toString()=="ADMIN");
		//countOderField.setVisible(userProfile.getId() == null);
		form.add(countOderField);
		
		CheckBox vipField = new CheckBox("vip");
		vipField.setVisible(AuthorizedSession.get().isSignedIn()&&AuthorizedSession.get().getRoles().toString()=="ADMIN");
		//vipField.setVisible(userProfile.getId() == null);
		form.add(vipField);
		
		DateTextField dateRegistrField = new DateTextField("dateRegistr", "dd-MM-yyyy");
		dateRegistrField.setVisible(AuthorizedSession.get().isSignedIn()&&AuthorizedSession.get().getRoles().toString()=="ADMIN");
		dateRegistrField.setEnabled(false);
		form.add(dateRegistrField);

		
        DropDownChoice<UserRole> roleField = new DropDownChoice<>("role", Arrays.asList(UserRole.values()), UserRoleChoiceRenderer.INSTANCE);        
        roleField.setVisible(AuthorizedSession.get().isSignedIn()&&AuthorizedSession.get().getRoles().toString()=="ADMIN");
        roleField.setRequired(true);
        form.add(roleField);
        
		CheckBox aceptRegistrField = new CheckBox("aceptRegistr");
		aceptRegistrField.setVisible(AuthorizedSession.get().isSignedIn()&&AuthorizedSession.get().getRoles().toString()=="ADMIN");
		form.add(aceptRegistrField);

	
		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {
				super.onSubmit();
			try{	
				if (userProfile.getId() == null) {
					userProfileService.registration(userProfile);
				} else {
					userProfileService.update(userProfile);
				}
				setResponsePage(new UsersPage());
			}catch (Exception e){
				error("User exist");
				}
			}
		});

		add(new FeedbackPanel("feedback"));

	    
	}

}
