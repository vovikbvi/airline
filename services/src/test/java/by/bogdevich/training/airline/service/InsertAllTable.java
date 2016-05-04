package by.bogdevich.training.airline.service;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.dataaccess.TicketDao;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.TicketClass;
import by.bogdevich.training.airline.datamodel.TicketTupe;
import by.bogdevich.training.airline.datamodel.UserProfile;
import by.bogdevich.training.airline.datamodel.UserRole;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class InsertAllTable {

	@Inject
	private CountryService countryService;

	@Inject
	private CityService cityService;

	@Inject
	private AirportService airportService;

	@Inject
	private FlightCatalogService flightCatalogService;

	@Inject
	private ManufacturedPlainService manufacturedPlameService;

	@Inject
	private ModelPlaneService modelPlaneService;

	@Inject
	private PlaneService planeService;

	@Inject
	private FlightService flightService;

	@Inject
	private UserProfileService userProfileService;

	@Inject
	private TicketService ticketService;

	private Country countryAdd() {
		Country country = new Country();
		country.setName("FR");
		countryService.insert(country);
		return countryService.get(country.getId());

	}

	@Test
	public void testCountry() {
		Assert.assertNotNull(countryAdd());
	}

	private City cityAdd() {
		City city = new City();
		city.setName("MySity");
		city.setTimezone(2.0);
		city.setCountry(countryAdd());

		cityService.insert(city);
		return cityService.get(city.getId());

	}

	@Test
	public void testCity() {

		Assert.assertNotNull(cityAdd());

	}

	private Airport airportAdd() {
		Airport airport = new Airport();

		airport.setName("Minsk");
		airport.setCodeIata("AAA");
		airport.setCodeIcao("QWEE");
		airport.setCity(cityAdd());
		airport.setClassWeight(ClassWeight.MEDIUM);
		airport.setCoordinatesX(2.3333);
		airport.setCoordinatesY(2.3434);

		airportService.insert(airport);
		return airportService.get(airport.getId());

	}

	@Test
	public void testAirport() {

		Assert.assertNotNull(airportAdd());
	}

	private FlightCatalog flightCatalogAdd() {
		FlightCatalog flightCatalog = new FlightCatalog();
		flightCatalog.setAirportStart(airportAdd());
		flightCatalog.setAirportFinish(airportAdd());
		flightCatalog.setDistance(1000);

		flightCatalogService.insert(flightCatalog);

		return flightCatalogService.get(flightCatalog.getId());

	}

	@Test
	public void testFlightCatalog() {
		Assert.assertNotNull(flightCatalogAdd());
	}

	private ManufacturedPlane manufacturedPlaneAdd() {
		ManufacturedPlane manufacturedPlane = new ManufacturedPlane();
		manufacturedPlane.setName("Boing");

		manufacturedPlameService.insert(manufacturedPlane);
		return manufacturedPlameService.get(manufacturedPlane.getId());

	}

	@Test
	public void testManufacturedPlane() {
		Assert.assertNotNull(manufacturedPlaneAdd());

	}

	private ModelPlane modelPlaneAdd() {
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
		return modelPlaneService.get(modelPlane.getId());

	}

	@Test
	public void testModelPlane() {
		Assert.assertNotNull(modelPlaneAdd());
	}

	private Plane planeAdd() {
		Plane plane = new Plane();
		plane.setBortNumber("PLANE31232");
		plane.setModelPlane(modelPlaneAdd());

		planeService.insert(plane);
		return planeService.get(plane.getId());
	}

	@Test
	public void testPlane() {
		Assert.assertNotNull(planeAdd());
	}

	private Flight flightAdd() {
		Flight flight = new Flight();
		flight.setFlightCatalog(flightCatalogAdd());
		flight.setRegistrTime(LocalDateTime.now());
		flight.setDepartureTime(LocalDateTime.now());
		flight.setArrivalTime(LocalDateTime.now());
		flight.setPlane(planeAdd());
		flight.setStartSaleTicket(LocalDateTime.now());

		flightService.insert(flight);
		return flightService.get(flight.getId());

	}

	@Test
	public void testFlight() {
		Flight flight = flightAdd();
		int ff = flightService.getColPassangersBuisnes(flight);
		Flight flightTest = flightAdd();
		Assert.assertNotNull(flightAdd());
	}

	private UserProfile userProfileAdd() {
		UserProfile userProfile = new UserProfile();
		userProfile.setLogin("login" + (int) (Math.random() * 10000));
		userProfile.setPassword("pas");
		userProfile.setFirstName("FirstName");
		userProfile.setLastName("LastName");
		userProfile.setEmail("vovik@mail.ru");
		userProfile.setPassportNumber("abcdfe");
		userProfile.setPhoneNumber("+375297121212");
		userProfile.setCountOder(1);
		userProfile.setVip(false);
		userProfile.setRole(UserRole.ADMIN);
		userProfile.setAceptRegistr(false);

		userProfileService.registration(userProfile);

		return userProfileService.get(userProfile.getId());
	}

	@Test
	public void testUserProfile() {

		Assert.assertNotNull(userProfileAdd());

	}

	private Ticket ticketAdd() {
		Ticket ticket = new Ticket();
		ticket.setFlight(flightAdd());
		ticket.setUserProfile(userProfileAdd());
		ticket.setPaid(true);
		ticket.setNumberSeats(2);
		ticket.setDateBought(LocalDateTime.now());
		ticket.setBaggage(false);
		ticket.setWeightBaggage(15.0);
		ticket.setTicketTupe(TicketTupe.SINGLE_TICKET);
		ticket.setTicketClass(TicketClass.ECONOMY);
		ticket.setPriorityRegistration(true);
		ticket.setPrioritySeats(true);
		ticket.setCosts(35.2);
		ticket.setForBaby(false);

		ticketService.insert(ticket);

		return ticketService.get(ticket.getId());

	}

	// @Test
	// public void

}
