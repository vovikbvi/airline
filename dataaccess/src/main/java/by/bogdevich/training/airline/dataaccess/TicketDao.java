package by.bogdevich.training.airline.dataaccess;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;


public interface TicketDao extends AbstractDao<Ticket, Long, TicketFilter>{
	
	List<Ticket> getRecordsSorted(TicketFilter filter);

	Double fiendBasicPrice (Date dateDeparture);

	Double countAllBaggage(Flight flight);

	List<Integer> getBasySeats(Flight flight, TicketClass ticketClass);

	Long countBusySeats(Flight flight, TicketClass ticketClass);

	Ticket getTicketWithFetch(Ticket ticket);

	void deleteDontPaidTicket();

	Boolean checkBusySeats(Integer numberSeats, TicketClass ticketClass, Flight flight);
}
