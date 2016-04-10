package by.bogdevich.training.airline.datamodel;

public class FlightCatalog extends AbstractModel {
	private Airport airportStart;
	private Airport airportFinish;
	private Integer Distance;
	private Integer basicPrice;
	private Integer priceBaggage;

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
		return Distance;
	}

	public void setDistance(Integer distance) {
		Distance = distance;
	}

	public Integer getBasicPrice() {
		return basicPrice;
	}

	public void setBasicPrice(Integer basicPrice) {
		this.basicPrice = basicPrice;
	}

	public Integer getPriceBaggage() {
		return priceBaggage;
	}

	public void setPriceBaggage(Integer priceBaggage) {
		this.priceBaggage = priceBaggage;
	}

}
