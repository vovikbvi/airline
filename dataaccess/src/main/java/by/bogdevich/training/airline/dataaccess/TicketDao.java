package by.bogdevich.training.airline.dataaccess;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;


public interface TicketDao extends AbstractDao<Ticket, Long, TicketFilter>{
	
	List<Ticket> getRecordsSorted(TicketFilter filter);

	Long countBusySeats(Flight flight);

	Double fiendBasicPrice (Date dateDeparture);

	Double countAllBaggage(Flight flight);

	Integer getColPassBuisnes();

}
