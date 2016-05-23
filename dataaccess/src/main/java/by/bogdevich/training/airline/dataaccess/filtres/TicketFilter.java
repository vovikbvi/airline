package by.bogdevich.training.airline.dataaccess.filtres;

import by.bogdevich.training.airline.datamodel.Flight;

public class TicketFilter extends AbstractFilter {

	private boolean fetchUser;
	private boolean fetchFlieght;

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

}
