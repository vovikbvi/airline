package by.bogdevich.training.airline.service;

import java.time.LocalDateTime;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class InsertAllTableTest {

	@Inject
	CountryService countryService;

	@Inject
	CityService cityService;

	@Inject
	AirportService airportService;

	@Inject
	FlightCatalogService flightCatalogService;

	@Inject
	FlightService flightService;
	
	//private Country country11 = countryAdd();
	
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

	private Flight flightAdd(){
		 Flight flight = new Flight();
		 flight.setFlightCatalog(flightCatalogAdd());
		 flight.setRegistrTime(LocalDateTime.now());
		 flight.setDepartureTime(LocalDateTime.now());
		 flight.setArrivalTime(LocalDateTime.now());
		 flight.setPlane(null);
		 flight.setStartSaleTicket(LocalDateTime.now());
		 
		 flightService.insert(flight);
		return flightService.get(flight.getId());
		
	}
	
	@Test
	public void testFlight(){
		Assert.assertNotNull(flightAdd());
	}

}
