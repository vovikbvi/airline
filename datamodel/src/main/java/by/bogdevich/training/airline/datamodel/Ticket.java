package by.bogdevich.training.airline.datamodel;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Entity
public class Ticket extends AbstractModel {
	
	@ManyToOne(targetEntity = Flight.class, fetch = FetchType.LAZY)	
	private Flight flight;
	
	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
	private User passanger;
	
	@Column
	private Boolean paid;
	
	@Column
	private Integer numberSeats;
	
	@Column
	private LocalDateTime dateBought;
	
	@Column
	private Boolean baggage;
	
	@Column
	private Double weightBaggage;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private TicketTupe ticketTupe;
	
	@Column
	@Enumerated(value = EnumType.STRING)
	private TicketClass ticketClass;
	
	@Column
	private Boolean priorityRegistration;
	
	@Column
	private Boolean prioritySeats;
	
	@Column
	private Double costs;
	
	@Column
	private Boolean forBaby;

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public User getPassanger() {
		return passanger;
	}

	public void setPassanger(User passanger) {
		this.passanger = passanger;
	}

	public Boolean getPaid() {
		return paid;
	}

	public void setPaid(Boolean paid) {
		this.paid = paid;
	}

	public Integer getNumberSeats() {
		return numberSeats;
	}

	public void setNumberSeats(Integer numberSeats) {
		this.numberSeats = numberSeats;
	}

	public LocalDateTime getDateBought() {
		return dateBought;
	}

	public void setDateBought(LocalDateTime dateBought) {
		this.dateBought = dateBought;
	}

	public Boolean getBaggage() {
		return baggage;
	}

	public void setBaggage(Boolean baggage) {
		this.baggage = baggage;
	}

	public Double getWeightBaggage() {
		return weightBaggage;
	}

	public void setWeightBaggage(Double weightBaggage) {
		this.weightBaggage = weightBaggage;
	}

	public TicketTupe getTicketTupe() {
		return ticketTupe;
	}

	public void setTicketTupe(TicketTupe ticketTupe) {
		this.ticketTupe = ticketTupe;
	}

	public TicketClass getTicketClass() {
		return ticketClass;
	}

	public void setTicketClass(TicketClass ticketClass) {
		this.ticketClass = ticketClass;
	}

	public Boolean getPriorityRegistration() {
		return priorityRegistration;
	}

	public void setPriorityRegistration(Boolean priorityRegistration) {
		this.priorityRegistration = priorityRegistration;
	}

	public Boolean getPrioritySeats() {
		return prioritySeats;
	}

	public void setPrioritySeats(Boolean prioritySeats) {
		this.prioritySeats = prioritySeats;
	}

	public Double getCosts() {
		return costs;
	}

	public void setCosts(Double costs) {
		this.costs = costs;
	}

	public Boolean getForBaby() {
		return forBaby;
	}

	public void setForBaby(Boolean forBaby) {
		this.forBaby = forBaby;
	}

}
