package by.bogdevich.training.airline.dataaccess.filtres;

import java.util.Date;

public class FlightFilter extends AbstractFilter {

	private Date startDepartureTime;
	private Date finishDepartureTime;
	private boolean setFetchFlieghtCatalog;

	public Date getStartDepartureTime() {
		return startDepartureTime;
	}

	public void setStartDepartureTime(Date startDepartureTime) {
		this.startDepartureTime = startDepartureTime;
	}

	public Date getFinishDepartureTime() {
		return finishDepartureTime;
	}

	public void setFinishDepartureTime(Date finishDepartureTime) {
		this.finishDepartureTime = finishDepartureTime;
	}

	public boolean isSetFetchFlieghtCatalog() {
		return setFetchFlieghtCatalog;
	}

	public void setSetFetchFlieghtCatalog(boolean setFetchFlieghtCatalog) {
		this.setFetchFlieghtCatalog = setFetchFlieghtCatalog;
	}

}
