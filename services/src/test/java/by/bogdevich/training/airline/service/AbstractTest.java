package by.bogdevich.training.airline.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.inject.Inject;

import org.junit.AfterClass;

import by.bogdevich.training.airline.dataaccess.FlightDao;
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

public class AbstractTest {
	@Inject
	protected CountryService countryService;

	@Inject
	protected CityService cityService;

	@Inject
	protected AirportService airportService;

	@Inject
	protected FlightCatalogService flightCatalogService;

	@Inject
	protected ManufacturedPlainService manufacturedPlameService;

	@Inject
	protected ModelPlaneService modelPlaneService;

	@Inject
	protected PlaneService planeService;

	@Inject
	protected FlightService flightService;

	@Inject
	protected UserProfileService userProfileService;

	@Inject
	protected TicketService ticketService;

	@Inject
	protected PriceService priceService;

	@Inject
	protected FlightDao flightDao;

	protected Country countryAdd() {
		Country country = new Country();
		country.setName("FR");
		countryService.insert(country);
		return countryService.get(country.getId());

	}

	protected City cityAdd() {
		City city = new City();
		city.setName("MySity");
		city.setTimezone(2.0);
		city.setCountry(countryAdd());

		cityService.insert(city);
		return cityService.get(city.getId());

	}

	protected Airport airportAdd() {
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

	protected FlightCatalog flightCatalogAdd() {
		FlightCatalog flightCatalog = new FlightCatalog();
		flightCatalog.setAirportStart(airportAdd());
		flightCatalog.setAirportFinish(airportAdd());
		flightCatalog.setDistance(1000);

		flightCatalogService.insert(flightCatalog);

		return flightCatalogService.get(flightCatalog.getId());

	}

	protected ManufacturedPlane manufacturedPlaneAdd() {
		ManufacturedPlane manufacturedPlane = new ManufacturedPlane();
		manufacturedPlane.setName("Boing");

		manufacturedPlameService.insert(manufacturedPlane);
		return manufacturedPlameService.get(manufacturedPlane.getId());

	}

	protected ModelPlane modelPlaneAdd() {
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

	protected Plane planeAdd() {
		Plane plane = new Plane();
		plane.setBortNumber("PLANE31232");
		plane.setModelPlane(modelPlaneAdd());

		planeService.insert(plane);
		return planeService.get(plane.getId());
	}

	protected Flight flightAdd() {
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

	protected UserProfile userProfileAdd() {
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

	protected Price priceAdd() {
		Price price = new Price();
		price.setBasicPrice(0.2);
		price.setDataChange(LocalDateTime.now());
		priceService.insert(price);

		return priceService.get(price.getId());
	}
	
	protected Ticket ticketAdd() {
		Ticket ticket = new Ticket();
		ticket.setFlight(flightAdd());
		ticket.setUserProfile(userProfileAdd());
		ticket.setPaid(true);
		ticket.setNumberSeats(2);
		ticket.setDateBought(LocalDateTime.now());
		ticket.setBaggage(true);
		ticket.setWeightBaggage(50.0);
		ticket.setTicketTupe(TicketTupe.SINGLE_TICKET);
		ticket.setTicketClass(TicketClass.ECONOMY);
		ticket.setPriorityRegistration(true);
		ticket.setPrioritySeats(true);
		//ticket.setCosts(35.2);
		ticket.setForBaby(false);

		ticketService.insert(ticket);

		return ticketService.get(ticket.getId());

	}


	protected void deletAllData() {
		List<Ticket> allTicket = ticketService.getAll();
		for (Ticket ticket : allTicket) {
			ticketService.delete(ticket.getId());
		}

		List<UserProfile> allUser = userProfileService.getAll();
		for (UserProfile userProfile : allUser) {
			userProfileService.delete(userProfile.getId());
		}
	/*	
		List<Price> allPrice = priceService.getAll();
		for (Price price : allPrice) {
			priceService.delete(price.getId());
		}
*/
		List<Flight> allFlight = flightService.getAll();
		for (Flight flight : allFlight) {
			flightService.delete(flight.getId());
		}

		List<Plane> allPlane = planeService.getAll();
		for (Plane plane : allPlane) {
			planeService.delete(plane.getId());
		}
		
		List<ModelPlane> allModelPlane = modelPlaneService.getAll();
		for (ModelPlane modelPlane : allModelPlane) {
			modelPlaneService.delete(modelPlane.getId());
		}

		List<ManufacturedPlane> allManufacturedPlane = manufacturedPlameService.getAll();
		for (ManufacturedPlane manufacturedPlane : allManufacturedPlane) {
			manufacturedPlameService.delete(manufacturedPlane.getId());
		}

		List<FlightCatalog> allFlightCatalog = flightCatalogService.getAll();
		for (FlightCatalog flightCatalog : allFlightCatalog) {
			flightCatalogService.delete(flightCatalog.getId());
		}
		
		List<Airport> allAirport = airportService.getAll();
		for (Airport airport : allAirport) {
			airportService.delete(airport.getId());
		}

		List<City> allCity = cityService.getAll();
		for (City city : allCity) {
			cityService.delete(city.getId());
		}

		List<Country> allCountry = countryService.getAll();
		for (Country country : allCountry) {
			countryService.delet(country.getId());
		}

	}

}
