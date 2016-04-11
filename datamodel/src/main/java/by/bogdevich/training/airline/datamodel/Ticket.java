package by.bogdevich.training.airline.datamodel;

import java.util.Date;

public class Ticket extends AbstractModel {
	private Flight flight;
	private User passanger;
	private Boolean paid;
	private Integer numberSeats;
	private Date dateBought;
	private Boolean baggage;
	private Double weightBaggage;
	private TicketTupe ticketTupe;
	private TicketClass ticketClass;
	private Boolean priorityRegistration;
	private Boolean prioritySeats;
	private Double costs;
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

	public Date getDateBought() {
		return dateBought;
	}

	public void setDateBought(Date dateBought) {
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
