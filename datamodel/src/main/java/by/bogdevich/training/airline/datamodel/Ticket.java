package by.bogdevich.training.airline.datamodel;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import by.bogdevich.training.airline.datamodel.util.LocalDateTimePersistenceConverter;

@Entity
public class Ticket extends AbstractModel {

	@ManyToOne(targetEntity = Flight.class, fetch = FetchType.LAZY)
	private Flight flight;

	@ManyToOne(targetEntity = UserProfile.class, fetch = FetchType.LAZY)
	private UserProfile userProfile;

	@Column
	private Boolean paid;

	@Column
	private Integer numberSeats;

	@Column
	private Date dateBought;

	@Column
	private Boolean baggage;

	@Column
	private Double weightBaggage;

	@Column
	@Enumerated(value = EnumType.ORDINAL)
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

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
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

	@Override
	public String toString() {
		return "Ticket [paid=" + paid + ", numberSeats=" + numberSeats + ", dateBought=" + dateBought + ", baggage="
				+ baggage + ", weightBaggage=" + weightBaggage + ", ticketClass=" + ticketClass
				+ ", priorityRegistration=" + priorityRegistration + ", prioritySeats=" + prioritySeats + ", costs="
				+ costs + ", forBaby=" + forBaby + "]";
	}



}
