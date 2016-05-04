package by.bogdevich.training.airline.datamodel;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import by.bogdevich.training.airline.datamodel.util.LocalDateTimePersistenceConverter;



@Entity
public class Flight extends AbstractModel {

	@ManyToOne(targetEntity = FlightCatalog.class, fetch = FetchType.LAZY)
	private FlightCatalog flightCatalog;

	@Column
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime registrTime;

	@Column
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime departureTime;

	@Column
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime arrivalTime;

	@ManyToOne(targetEntity = Plane.class, fetch = FetchType.LAZY)
	private Plane plane;

	@Column
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime startSaleTicket;
	


	public FlightCatalog getFlightCatalog() {
		return flightCatalog;
	}

	public void setFlightCatalog(FlightCatalog flightsCatalog) {
		this.flightCatalog = flightsCatalog;
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

	public void setPlane(Plane plane_id) {
		this.plane = plane_id;
	}

	public LocalDateTime getStartSaleTicket() {
		return startSaleTicket;
	}

	public void setStartSaleTicket(LocalDateTime startSaleTicket) {
		this.startSaleTicket = startSaleTicket;
	}

}
