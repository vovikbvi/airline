package by.bogdevich.training.airline.datamodel;

import java.sql.Date;

public class Flight extends AbstractModel {
	private FlightCatalog flightsCatalog;
	private Date departureTime;
	private Date arrivalTime;
	private Plane plane;
	private Date startSaleTicket;

	public FlightCatalog getFlightsCatalog() {
		return flightsCatalog;
	}

	public void setFlightsCatalog(FlightCatalog flightsCatalog) {
		this.flightsCatalog = flightsCatalog;
	}

	public Date getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(Date departureTime) {
		this.departureTime = departureTime;
	}

	public Date getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(Date arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public Date getStartSaleTicket() {
		return startSaleTicket;
	}

	public void setStartSaleTicket(Date startSaleTicket) {
		this.startSaleTicket = startSaleTicket;
	}

}
