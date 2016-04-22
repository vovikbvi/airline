package by.bogdevich.training.airline.service;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.User;

public interface TicketService {

	void orderTicket(User user, Flight flight);
	
	void deleteTicket(Ticket ticket);
}
