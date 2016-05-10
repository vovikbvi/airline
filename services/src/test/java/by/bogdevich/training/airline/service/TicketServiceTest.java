package by.bogdevich.training.airline.service;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.TicketTupe;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class TicketServiceTest extends AbstractTest {

	@Inject
	TicketService ticketService;

	@Inject
	protected FlightService flightService;

	@Test
	public void colTicketTest() {

		Flight flight = new Flight();
		flight.setFlightCatalog(flightCatalogAdd());
		flight.setRegistrTime(LocalDateTime.now());
		flight.setDepartureTime(LocalDateTime.now().plusDays(20));
		flight.setArrivalTime(LocalDateTime.now());
		flight.setPlane(planeAdd());
		flight.setStartSaleTicket(LocalDateTime.now().minusDays(10));
		flightService.insert(flight);

		for (int i = 0; i < 10; i++) {
			Ticket ticket = new Ticket();
			ticket.setFlight(flight);
			ticket.setUserProfile(userProfileAdd());
			ticket.setPaid(true);
			ticket.setNumberSeats(2);
			ticket.setDateBought(LocalDateTime.now());
			ticket.setBaggage(false);
			ticket.setWeightBaggage(15.0);
			ticket.setTicketTupe(TicketTupe.SINGLE_TICKET);
			ticket.setTicketClass(TicketClass.FIRST_CLASS);
			ticket.setPriorityRegistration(true);
			ticket.setPrioritySeats(true);
			ticket.setCosts(35.2);
			ticket.setForBaby(false);

			ticketService.insert(ticket);

		}
	}
}
