package by.bogdevich.training.airline.dataaccess.filtres;

public class UserProfileFilter extends AbstractFilter {

	private Boolean showLogin;
	private String firstName;
	private boolean isFetchCredentials;

	public Boolean getShowLogin() {
		return showLogin;
	}

	public void setShowLogin(Boolean showLogin) {
		this.showLogin = showLogin;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public boolean isFetchCredentials() {
		return isFetchCredentials;
	}

	public void setFetchCredentials(boolean isFetchCredentials) {
		this.isFetchCredentials = isFetchCredentials;
	}

}
