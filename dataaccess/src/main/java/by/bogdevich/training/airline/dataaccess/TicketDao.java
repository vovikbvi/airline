package by.bogdevich.training.airline.dataaccess;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import by.bogdevich.training.airline.dataaccess.filtres.TicketFilter;
import by.bogdevich.training.airline.dataaccess.filtres.UserProfileFilter;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.UserProfile;

public interface TicketDao extends AbstractDao<Ticket, Long>{
	
	Integer getColPassangerB();

	List<Ticket> getRecordsSorted(TicketFilter filter);

	Long countBusySeats(Flight flight);

	Integer fiendDate(LocalDateTime dateDeparture);

}
