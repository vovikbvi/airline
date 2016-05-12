package by.bogdevich.training.airline.dataaccess.filtres;

public class FlightCatalogFilter extends AbstractFilter {

	private boolean isFetchAirportStart;
	private boolean isFetchAirportFinish;

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

}
