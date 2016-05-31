package by.bogdevich.training.airline.dataaccess.filtres;

public class FlightCatalogFilter extends AbstractFilter {

	private boolean isFetchAirportStart;
	private boolean isFetchAirportFinish;
	private String cityStart;
	private String cityFinish;
	

	public boolean isFetchAirportStart() {
		return isFetchAirportStart;
	}

	public void setFetchAirportStart(boolean isFetchAirportStart) {
		this.isFetchAirportStart = isFetchAirportStart;
	}

	public boolean isFetchAirportFinish() {
		return isFetchAirportFinish;
	}

	public void setFetchAirportFinish(boolean isFetchAirportFinish) {
		this.isFetchAirportFinish = isFetchAirportFinish;
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
