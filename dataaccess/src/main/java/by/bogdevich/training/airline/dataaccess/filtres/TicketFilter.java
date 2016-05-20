package by.bogdevich.training.airline.dataaccess.filtres;

import by.bogdevich.training.airline.datamodel.Flight;

public class TicketFilter extends AbstractFilter {

	private boolean setFetchUser;
	private boolean setFetchFlieght;

	public boolean isSetFetchUser() {
		return setFetchUser;
	}

	public void setSetFetchUser(boolean setFetchUser) {
		this.setFetchUser = setFetchUser;
	}

	public boolean isSetFetchFlieght() {
		return setFetchFlieght;
	}

	public void setSetFetchFlieght(boolean setFetchFlieght) {
		this.setFetchFlieght = setFetchFlieght;
	}

}
