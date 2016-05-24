package by.bogdevich.training.airline.dataaccess.filtres;

public class AirportFilter extends AbstractFilter {

	private String airportName;
	private boolean fetchCity;

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public boolean isFetchCity() {
		return fetchCity;
	}

	public void setFetchCity(boolean fetchCity) {
		this.fetchCity = fetchCity;
	}

}
