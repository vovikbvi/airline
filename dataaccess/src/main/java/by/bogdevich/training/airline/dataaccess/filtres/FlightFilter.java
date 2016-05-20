package by.bogdevich.training.airline.dataaccess.filtres;

import java.time.LocalDateTime;

public class FlightFilter extends AbstractFilter {

	private LocalDateTime startDepartureTime;
	private LocalDateTime finishDepartureTime;
	private boolean setFetchFlieghtCatalog;

	public LocalDateTime getStartDepartureTime() {
		return startDepartureTime;
	}

	public void setStartDepartureTime(LocalDateTime startDepartureTime) {
		this.startDepartureTime = startDepartureTime;
	}

	public LocalDateTime getFinishDepartureTime() {
		return finishDepartureTime;
	}

	public void setFinishDepartureTime(LocalDateTime finishDepartureTime) {
		this.finishDepartureTime = finishDepartureTime;
	}

	public boolean isSetFetchFlieghtCatalog() {
		return setFetchFlieghtCatalog;
	}

	public void setSetFetchFlieghtCatalog(boolean setFetchFlieghtCatalog) {
		this.setFetchFlieghtCatalog = setFetchFlieghtCatalog;
	}

}
