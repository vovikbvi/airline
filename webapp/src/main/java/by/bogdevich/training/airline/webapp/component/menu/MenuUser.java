package by.bogdevich.training.airline.webapp.component.menu;

import org.apache.wicket.authorization.Action;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;
import by.bogdevich.training.airline.webapp.app.AuthorizedSession;
import by.bogdevich.training.airline.webapp.page.feedback.FeedbackPage;
import by.bogdevich.training.airline.webapp.page.home.HomePage;
import by.bogdevich.training.airline.webapp.page.info.FlightInfoPage;
import by.bogdevich.training.airline.webapp.page.login.LoginPage;
import by.bogdevich.training.airline.webapp.page.myoders.MyOdersPage;
import by.bogdevich.training.airline.webapp.page.registr.RegistrationPage;
import by.bogdevich.training.airline.webapp.page.schedule.SchedulePage;
import by.bogdevich.training.airline.webapp.page.setting.SettingPage;

public class MenuUser extends Panel {

	public MenuUser(String id) {
		super(id);
		// setRenderBodyOnly(true);
	}

	@Override
	protected void onInitialize() {
		super.onInitialize();

		add(new Link("home-page") {
			@Override
			public void onClick() {
				setResponsePage(new HomePage());
			}
		});

		add(new Link("schedule") {
			@Override
			public void onClick() {
				setResponsePage(new SchedulePage());
			}
		});
/*
		add(new Link("search") {
			@Override
			public void onClick() {
				setResponsePage(new SearchPage());
			}
		});
*/
		Link myOders = new Link("my-oders") {
			@Override
			public void onClick() {
				setResponsePage(new MyOdersPage());
			}
		};
		add(myOders);
		myOders.setEnabled(AuthorizedSession.get().isSignedIn());
		
		Link myProfile = new Link("my-profile") {
			@Override
			public void onClick() {
				setResponsePage(new RegistrationPage(AuthorizedSession.get().getLoggedUser()));
			}
		};
		add(myProfile);
		myProfile.setEnabled(AuthorizedSession.get().isSignedIn());

		add(new Link("feedback") {
			@Override
			public void onClick() {
				setResponsePage(new FeedbackPage());
			}
		});

		Link settingsLink = new Link("setting") {
			@Override
			public void onClick() {
				setResponsePage(new SettingPage());
			}
		};
		
		add(settingsLink);
		
		boolean isLoginAdminOrOperator;
		boolean isLogIn = AuthorizedSession.get().isSignedIn();
		
		if (isLogIn && AuthorizedSession.get().getLoggedUser().getRole() == UserRole.ADMIN){
		isLoginAdminOrOperator = true;
		}else if(isLogIn && AuthorizedSession.get().getLoggedUser().getRole() == UserRole.OPERATOR){
			isLoginAdminOrOperator = true;
		}else{
			isLoginAdminOrOperator = false;
		}

			settingsLink.setVisible(isLoginAdminOrOperator);

		Link logIn = new Link("login") {
			@Override
			public void onClick() {
				setResponsePage(new LoginPage());
			}
		};
		add(logIn);
		
		Link registration = new Link("registration") {
			@Override
			public void onClick() {
				setResponsePage(new RegistrationPage(new UserProfile()));
			}
		};
		add(registration);
		
		

		Link logOut = new Link("logout") {
			@Override
			public void onClick() {
				getSession().invalidate();
				setResponsePage(new HomePage());
			}
		};
		add(logOut);

		if (AuthorizedSession.get().isSignedIn()) {
			logIn.setVisible(false);
			logOut.setVisible(true);
		} else {
			logOut.setVisible(false);
			logIn.setVisible(true);
		}

	}
}
