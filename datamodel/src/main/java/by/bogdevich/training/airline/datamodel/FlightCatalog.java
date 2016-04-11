package by.bogdevich.training.airline.datamodel;

public class FlightCatalog extends AbstractModel {
	private Airport airportStart;
	private Airport airportFinish;
	private Integer distance;
	private Boolean international;

	public Airport getAirportStart() {
		return airportStart;
	}

	public void setAirportStart(Airport airportStart) {
		this.airportStart = airportStart;
	}

	public Airport getAirportFinish() {
		return airportFinish;
	}

	public void setAirportFinish(Airport airportFinish) {
		this.airportFinish = airportFinish;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Boolean getInternational() {
		return international;
	}

	public void setInternational(Boolean international) {
		this.international = international;
	}

}
