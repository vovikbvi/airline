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
import by.bogdevich.training.airline.datamodel.Price;
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

	@Inject
	private PriceService priceService;

	private Country countryAdd() {
		Country country = new Country();
		country.setName("FR");
		countryService.insert(country);
		return countryService.get(country.getId());

	}

	// test country

	@Test
	public void testAddCountry() {
		Assert.assertNotNull(countryAdd());
	}

	@Test
	public void testUpdateCountry() {
		String updatedField = "updatedName";
		Country country = countryAdd();
		country.setName(updatedField);
		countryService.update(country);

		Assert.assertEquals(updatedField, countryService.get(country.getId()).getName());
	}

	@Test
	public void testDeletCountry() {
		Country country = countryAdd();
		countryService.delet(country.getId());

		Assert.assertNull(countryService.get(country.getId()));
	}

	// end test country

	private City cityAdd() {
		City city = new City();
		city.setName("MySity");
		city.setTimezone(2.0);
		city.setCountry(countryAdd());

		cityService.insert(city);
		return cityService.get(city.getId());

	}

	// test city

	@Test
	public void testAddCity() {

		Assert.assertNotNull(cityAdd());

	}

	@Test
	public void testUpdateCity() {
		String updatedField = "updatedName";
		City city = cityAdd();
		city.setName(updatedField);
		cityService.update(city);

		Assert.assertEquals(updatedField, cityService.get(city.getId()).getName());
	}

	@Test
	public void testDeletCity() {
		City city = cityAdd();
		cityService.delete(city.getId());

		Assert.assertNull(cityService.get(city.getId()));
	}

	// end test city

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

	// test airport
	@Test
	public void testAddAirport() {

		Assert.assertNotNull(airportAdd());
	}

	@Test
	public void testUpdateAirport() {
		String updatedField = "updatedName";
		Airport airport = airportAdd();
		airport.setName(updatedField);
		airportService.update(airport);

		Assert.assertEquals(updatedField, airportService.get(airport.getId()).getName());
	}

	@Test
	public void testDeletAirport() {
		Airport airport = airportAdd();
		airportService.delete(airport.getId());

		Assert.assertNull(airportService.get(airport.getId()));
	}

	// end test airport

	private FlightCatalog flightCatalogAdd() {
		FlightCatalog flightCatalog = new FlightCatalog();
		flightCatalog.setAirportStart(airportAdd());
		flightCatalog.setAirportFinish(airportAdd());
		flightCatalog.setDistance(1000);

		flightCatalogService.insert(flightCatalog);

		return flightCatalogService.get(flightCatalog.getId());

	}

	// test flight catalog

	@Test
	public void testAddFlightCatalog() {
		Assert.assertNotNull(flightCatalogAdd());
	}

	@Test
	public void testUpdateFlightCatalog() {
		Integer updatedField = 200;
		FlightCatalog flightCatalog = flightCatalogAdd();
		flightCatalog.setDistance(updatedField);
		flightCatalogService.update(flightCatalog);

		Assert.assertEquals((long) updatedField, (long) flightCatalogService.get(flightCatalog.getId()).getDistance());
	}

	@Test
	public void testDeletFlightCatalog() {
		FlightCatalog flightCatalog = flightCatalogAdd();
		flightCatalogService.delete(flightCatalog.getId());

		Assert.assertNull(flightCatalogService.get(flightCatalog.getId()));
	}

	// end test flight catalog

	private ManufacturedPlane manufacturedPlaneAdd() {
		ManufacturedPlane manufacturedPlane = new ManufacturedPlane();
		manufacturedPlane.setName("Boing");

		manufacturedPlameService.insert(manufacturedPlane);
		return manufacturedPlameService.get(manufacturedPlane.getId());

	}

	// test Manufactured Plane

	@Test
	public void testAddManufacturedPlane() {
		Assert.assertNotNull(manufacturedPlaneAdd());

	}

	@Test
	public void testUpdateManufacturedPlane() {
		String updatedField = "UpdateField";
		ManufacturedPlane manufacturedPlane = manufacturedPlaneAdd();
		manufacturedPlane.setName(updatedField);
		manufacturedPlameService.update(manufacturedPlane);

		Assert.assertEquals(updatedField, manufacturedPlameService.get(manufacturedPlane.getId()).getName());
	}

	@Test
	public void testDeletManufacturedPlane() {
		ManufacturedPlane manufacturedPlane = manufacturedPlaneAdd();
		manufacturedPlameService.delete(manufacturedPlane.getId());

		Assert.assertNull(manufacturedPlameService.get(manufacturedPlane.getId()));
	}

	// end test Manufactured Plane

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

	// test model plane

	@Test
	public void testAddModelPlane() {
		Assert.assertNotNull(modelPlaneAdd());
	}

	@Test
	public void testUpdateModelPlane() {
		int updatedField = 62;
		ModelPlane modelPlane = modelPlaneAdd();
		modelPlane.setColPassangersBuisnes(updatedField);
		modelPlaneService.update(modelPlane);

		Assert.assertEquals((long) updatedField,
				(long) modelPlaneService.get(modelPlane.getId()).getColPassangersBuisnes());
	}

	@Test
	public void testDeletModelPlane() {
		ModelPlane modelPlane = modelPlaneAdd();
		modelPlaneService.delete(modelPlane.getId());

		Assert.assertNull(modelPlaneService.get(modelPlane.getId()));
	}

	// end test model plane

	private Plane planeAdd() {
		Plane plane = new Plane();
		plane.setBortNumber("PLANE31232");
		plane.setModelPlane(modelPlaneAdd());

		planeService.insert(plane);
		return planeService.get(plane.getId());
	}

	
	
	//test plane
	
	@Test
	public void testAddPlane() {
		Assert.assertNotNull(planeAdd());
	}
	
	@Test
	public void testUpdatePlane() {
		String updatedField = "new field";
		Plane plane = planeAdd();
		plane.setBortNumber(updatedField);
		planeService.update(plane);

		Assert.assertEquals(updatedField, planeService.get(plane.getId()).getBortNumber());
	}

	@Test
	public void testDeletPlane() {
		Plane plane = planeAdd();
		planeService.delete(plane.getId());

		Assert.assertNull(planeService.get(plane.getId()));
	}

	
	//end test plane
	
	
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

	@Test
	public void testAddTicket() {
		Assert.assertNotNull(ticketAdd());
	}

	private Price priceAdd() {
		Price price = new Price();
		price.setBasicPrice(15.2);
		price.setDataChange(LocalDateTime.now());
		priceService.insert(price);

		return priceService.get(price.getId());
	}

	@Test
	public void testPrice() {
		Assert.assertNotNull(priceAdd());
	}

}
