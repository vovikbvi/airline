package by.bogdevich.training.airline.datamodel;

import java.util.Date;
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
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date registrTime;

	@Column
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date departureTime;

	@Column
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date arrivalTime;

	@ManyToOne(targetEntity = Plane.class, fetch = FetchType.LAZY)
	private Plane plane;

	@Column
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date startSaleTicket;
	

    @OneToMany(mappedBy = "flight", fetch = FetchType.LAZY)
    private List<Ticket> Tickets;


	public FlightCatalog getFlightCatalog() {
		return flightCatalog;
	}


	public void setFlightCatalog(FlightCatalog flightCatalog) {
		this.flightCatalog = flightCatalog;
	}


	public Date getRegistrTime() {
		return registrTime;
	}


	public void setRegistrTime(Date registrTime) {
		this.registrTime = registrTime;
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


	@Override
	public String toString() {
		return "Flight [flightCatalog=" + flightCatalog + ", registrTime=" + registrTime + ", departureTime="
				+ departureTime + ", arrivalTime=" + arrivalTime + ", plane=" + plane + ", startSaleTicket="
				+ startSaleTicket + ", Tickets=" + Tickets + "]";
	}


}
