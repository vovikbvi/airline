package by.bogdevich.training.airline.dataaccess.filtres;

import by.bogdevich.training.airline.datamodel.UserProfile;

public class TicketFilter extends AbstractFilter {

	private boolean fetchUser;
	private boolean fetchFlieght;
	private boolean fetchAirport;
	private UserProfile user;

	public boolean getFetchUser() {
		return fetchUser;
	}

	public void setFetchUser(boolean fetchUser) {
		this.fetchUser = fetchUser;
	}

	public boolean getFetchFlieght() {
		return fetchFlieght;
	}

	public void setFetchFlieght(boolean fetchFlieght) {
		this.fetchFlieght = fetchFlieght;
	}

	public UserProfile getUser() {
		return user;
	}

	public void setUser(UserProfile user) {
		this.user = user;
	}

	public boolean getFetchAirport() {
		return fetchAirport;
	}

	public void setFetchAirport(boolean fetchAirport) {
		this.fetchAirport = fetchAirport;
	}


	
}
