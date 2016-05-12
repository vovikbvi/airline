package by.bogdevich.training.airline.dataaccess.filtres;

public class UserProfileFilter extends AbstractFilter {

	private boolean setFetchTickets;
	private String firstName;
	

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


}
