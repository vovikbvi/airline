package by.bogdevich.training.airline.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.TicketTupe;

import java.util.Date;

import org.junit.Assert;;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class TicketServiceTest extends AbstractTest {

	@Test
	public void testAddTicket() {
		Assert.assertNotNull(ticketAdd());
	}

	@Test
	public void testUpdateTicket() {
		Integer updatedField = 4;
		Ticket ticket = ticketAdd();
		ticket.setNumberSeats(updatedField);
		ticketService.update(ticket);

		Assert.assertEquals((long) updatedField, (long) ticketService.get(ticket.getId()).getNumberSeats());
	}

	@Test
	public void testDeletTicket() {
		Ticket ticket = ticketAdd();
		ticketService.delete(ticket.getId());

		Assert.assertNull(ticketService.get(ticket.getId()));
	}
/*
	@Test
	public void calculationParamTicketTest() {

		Flight flight = new Flight();
		flight.setFlightCatalog(flightCatalogAdd());
		flight.setRegistrTime(new Date());
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
			ticket.setBaggage(true);
			ticket.setWeightBaggage(50.0);
			ticket.setTicketTupe(TicketTupe.SINGLE_TICKET);
			ticket.setTicketClass(TicketClass.FIRST_CLASS);
			ticket.setPriorityRegistration(true);
			ticket.setPrioritySeats(true);
			ticket.setForBaby(false);

			ticketService.insert(ticket);

		}
	}
*/
	
/*
	@Test
	public void calculationCostTest() {

		ModelPlane modelPlane = new ModelPlane();
		modelPlane.setManufacturedPlane(manufacturedPlaneAdd());
		modelPlane.setModel("Boing-1515");
		modelPlane.setColPassangersBuisnes(20);
		modelPlane.setColPassangersFirstclass(30);
		modelPlane.setColPassangersEconomy(15);
		modelPlane.setWeightAllBaggage(300);
		modelPlane.setAvgSpeed(340);
		modelPlane.setClassWeight(ClassWeight.MEDIUM);

		modelPlaneService.insert(modelPlane);

		Plane plane = new Plane();
		plane.setBortNumber("PLANE31232");
		plane.setModelPlane(modelPlaneAdd());

		planeService.insert(plane);

		FlightCatalog flightCatalog = new FlightCatalog();
		flightCatalog.setAirportStart(airportAdd());
		flightCatalog.setAirportFinish(airportAdd());
		flightCatalog.setDistance(1000);

		flightCatalogService.insert(flightCatalog);

		Flight flight = new Flight();
		flight.setFlightCatalog(flightCatalogAdd());
		flight.setRegistrTime(LocalDateTime.now());
		flight.setDepartureTime(LocalDateTime.now().plusDays(10));
		flight.setArrivalTime(LocalDateTime.now());
		flight.setPlane(planeAdd());
		flight.setStartSaleTicket(LocalDateTime.now().minusDays(10));
		flightService.insert(flight);

		Ticket ticket = new Ticket();
		ticket.setFlight(flight);
		ticket.setUserProfile(userProfileAdd());
		ticket.setPaid(true);
		ticket.setNumberSeats(2);
		ticket.setDateBought(LocalDateTime.now());
		ticket.setBaggage(true);
		ticket.setWeightBaggage(50.0);
		ticket.setTicketTupe(TicketTupe.SINGLE_TICKET);
		ticket.setTicketClass(TicketClass.FIRST_CLASS);
		ticket.setPriorityRegistration(true);
		ticket.setPrioritySeats(true);
		ticket.setForBaby(false);

		ticketService.insert(ticket);

		// ручной расчет

		double price = 1000 * 0.2;
		double factorBusySeats = price * 0.2 * 0;
		double factorToDateDeparture = price * 0.2 * 0.48;
		double factorPrioritySeats = 20;
		double factorProrityRegistration = 15;
		double factorForBaby = 0;
		double costBaggage = price + (price * 50 / 100);

		double result = price + factorBusySeats + factorToDateDeparture + factorPrioritySeats
				+ factorProrityRegistration - factorForBaby + costBaggage;

		Assert.assertEquals(result, ticket.getCosts(), 3);
	}
*/
}
