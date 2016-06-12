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
import org.apache.wicket.markup.html.link.BookmarkablePageLink;
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

public class ConfirmRegistrationPage extends AbstractPage {

	@Inject
	private UserProfileService userProfileService;

	private UserProfile userProfile;


	public ConfirmRegistrationPage(UserProfile userProfile) {
		super();
		this.userProfile = userProfile;		
		
	      PageParameters parameters = new PageParameters();
	        parameters.set("name", userProfile.getId());
	        add(new BookmarkablePageLink("pageLinkWithArgs", BookmarkablePageLink.class, parameters));
	}


}
