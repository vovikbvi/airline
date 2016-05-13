package by.bogdevich.training.airline.datamodel;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class FlightCatalog extends AbstractModel {

	// add colum name !!!!!!!!!
	
	@ManyToOne(targetEntity = Airport.class, fetch = FetchType.LAZY)
	private Airport airportStart;

	@ManyToOne(targetEntity = Airport.class, fetch = FetchType.LAZY)
	private Airport airportFinish;

	@Column
	private Integer distance;

	@Column
	private Boolean international;
	
    @OneToMany(mappedBy = "flightCatalog", fetch = FetchType.LAZY)
    private List<Flight> flights;


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

	@Override
	public String toString() {
		return "FlightCatalog [airportStart=" + airportStart + ", airportFinish=" + airportFinish + ", distance="
				+ distance + ", international=" + international + "]";
	}

	
}
