package by.bogdevich.training.airline.webapp.app;

import java.util.Locale;

import javax.inject.Inject;

import org.apache.wicket.Session;
import org.apache.wicket.authroles.authentication.AuthenticatedWebSession;
import org.apache.wicket.authroles.authorization.strategies.role.Roles;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.request.Request;

import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.service.UserProfileService;
import by.bogdevich.training.airline.webapp.component.licalization.LanguageSelectionComponent;

public class AuthorizedSession extends AuthenticatedWebSession {
    @Inject
    private UserProfileService userProfileService;

    private UserProfile loggedUser;

    private Roles roles;

    public AuthorizedSession(Request request) {
        super(request);
        Injector.get().inject(this);

    }

    public static AuthorizedSession get() {
        return (AuthorizedSession) Session.get();
    }

    @Override
    public Locale getLocale() {
        Locale locale = super.getLocale();
        if (locale == null || !LanguageSelectionComponent.SUPPORTED_LOCALES.contains(locale)) {
            setLocale(LanguageSelectionComponent.SUPPORTED_LOCALES.get(0));
        }
        return super.getLocale();
    }

    
    @Override
    public boolean authenticate(final String userName, final String password) {
        loggedUser = userProfileService.getPermissions(userName, password);
        return loggedUser != null;
    }

    @Override
    public Roles getRoles() {
        if (isSignedIn() && (roles == null)) {
            roles = new Roles();
            roles.addAll(userProfileService.resolveRoles(loggedUser.getId()));
        }
        return roles;
    }

    @Override
    public void signOut() {
        super.signOut();
        loggedUser = null;
        roles = null;
    }

    public UserProfile getLoggedUser() {
        return loggedUser;
    }

}
