package by.bogdevich.training.airline.dataaccess.filtres;

public class UserProfileFilter extends AbstractFilter {

	private boolean setFetchTickets;
	private String firstName;
	private String login;
	

	public boolean isSetFetchTickets() {
		return setFetchTickets;
	}

	public void setSetFetchTickets(boolean setFetchTickets) {
		this.setFetchTickets = setFetchTickets;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}


}
