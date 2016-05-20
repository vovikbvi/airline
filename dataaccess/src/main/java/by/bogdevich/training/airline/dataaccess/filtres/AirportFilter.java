package by.bogdevich.training.airline.dataaccess.filtres;

public class AirportFilter extends AbstractFilter{
	
	private String airportName;
	private boolean setFetchCity;

	public String getAirportName() {
		return airportName;
	}

	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public boolean isSetFetchCity() {
		return setFetchCity;
	}

	public void setSetFetchCity(boolean setFetchCity) {
		this.setFetchCity = setFetchCity;
	}

	
}
