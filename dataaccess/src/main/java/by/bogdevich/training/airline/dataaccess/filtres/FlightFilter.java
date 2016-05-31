package by.bogdevich.training.airline.dataaccess.filtres;

import java.util.Date;

public class FlightFilter extends AbstractFilter {

	private Date startDepartureTime;
	private Date finishDepartureTime;
	private String cityStart;
	private String cityFinish;
	private boolean fetchFlieghtCatalog;
	private boolean fetchPlane;

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

	public boolean isFetchFlieghtCatalog() {
		return fetchFlieghtCatalog;
	}

	public void setFetchFlieghtCatalog(boolean fetchFlieghtCatalog) {
		this.fetchFlieghtCatalog = fetchFlieghtCatalog;
	}

	public boolean isFetchPlane() {
		return fetchPlane;
	}

	public void setFetchPlane(boolean fetchPlane) {
		this.fetchPlane = fetchPlane;
	}

	public String getCityStart() {
		return cityStart;
	}

	public void setCityStart(String cityStart) {
		this.cityStart = cityStart;
	}

	public String getCityFinish() {
		return cityFinish;
	}

	public void setCityFinish(String cityFinish) {
		this.cityFinish = cityFinish;
	}

}
