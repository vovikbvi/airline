package by.bogdevich.training.airline.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Flight extends AbstractModel {

	@ManyToOne(targetEntity = FlightCatalog.class, fetch = FetchType.LAZY)
	private FlightCatalog flightsCatalog;

	@Column
	private LocalDateTime registrTime;

	@Column
	private LocalDateTime departureTime;

	@Column
	private LocalDateTime arrivalTime;

	@ManyToOne(targetEntity = Plane.class, fetch = FetchType.LAZY)
	private Plane plane;

	@Column
	private LocalDateTime startSaleTicket;

	public FlightCatalog getFlightsCatalog() {
		return flightsCatalog;
	}

	public void setFlightsCatalog(FlightCatalog flightsCatalog) {
		this.flightsCatalog = flightsCatalog;
	}

	public LocalDateTime getRegistrTime() {
		return registrTime;
	}

	public void setRegistrTime(LocalDateTime registrTime) {
		this.registrTime = registrTime;
	}

	public LocalDateTime getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(LocalDateTime departureTime) {
		this.departureTime = departureTime;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public Plane getPlane() {
		return plane;
	}

	public void setPlane(Plane plane) {
		this.plane = plane;
	}

	public LocalDateTime getStartSaleTicket() {
		return startSaleTicket;
	}

	public void setStartSaleTicket(LocalDateTime startSaleTicket) {
		this.startSaleTicket = startSaleTicket;
	}

}
