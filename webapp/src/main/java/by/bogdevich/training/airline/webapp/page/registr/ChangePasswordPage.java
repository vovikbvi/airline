package by.bogdevich.training.airline.webapp.page.registr;

import java.util.Arrays;
import java.util.Date;

import javax.inject.Inject;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.SubmitLink;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.page.AbstractPage;
import by.bogdevich.training.airline.webapp.page.home.HomePage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;

public class ChangePasswordPage extends AbstractPage {

	@Inject
	private UserProfileService userProfileService;

	private UserProfile userProfile;

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;

	public ChangePasswordPage(UserProfile userProfile) {
		super();
		this.userProfile = userProfile;
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();


		Form<?> form = new Form<Void>("form");
		add(form);
		form.setDefaultModel(new CompoundPropertyModel(this));
		PasswordTextField oldPasswordField = new PasswordTextField("oldPassword");
		oldPasswordField.setRequired(true);
		form.add(oldPasswordField);

		PasswordTextField newPasswordField = new PasswordTextField("newPassword");
		newPasswordField.setRequired(true);
		form.add(newPasswordField);

		PasswordTextField confirmPasswordField = new PasswordTextField("confirmPassword");
		confirmPasswordField.setRequired(true);
		form.add(confirmPasswordField);

		form.add(new SubmitLink("save") {
			@Override
			public void onSubmit() {

				super.onSubmit();
				if (!userProfile.getPassword().equals(oldPassword)) {
					info(getString("user.incorrect_old_password"));
				} else if (!newPassword.equals(confirmPassword)) {
					info(getString("user.passwords_not_same"));
				} else {
					userProfile.setPassword(newPassword);
					userProfileService.update(userProfile);
					setResponsePage(new HomePage());
				}
			}

		});

		add(new FeedbackPanel("feedback"));

	}

}
