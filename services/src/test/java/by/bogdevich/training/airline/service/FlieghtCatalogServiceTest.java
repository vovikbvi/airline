package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.FlightCatalog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class FlieghtCatalogServiceTest extends AbstractTest{

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
	//@Before
	public void dell(){
		deletAllData();
	} 

	@Test
	public void testFlightInternational(){
		
		Country countryStart = new Country();
		countryStart.setName("BY");
		countryService.insert(countryStart);
		

		City cityStart = new City();
		cityStart.setName("MySity");
		cityStart.setTimezone(2.0);
		cityStart.setCountry(countryStart);
		cityService.insert(cityStart);

		Airport airportStart = new Airport();
		airportStart.setName("Minsk");
		airportStart.setCodeIata("AAA");
		airportStart.setCodeIcao("QWEE");
		airportStart.setCity(cityStart);
		airportStart.setClassWeight(ClassWeight.MEDIUM);
		airportStart.setCoordinatesX(2.3333);
		airportStart.setCoordinatesY(2.3434);
		airportService.insert(airportStart);

		Country countryFinish = new Country();
		countryFinish.setName("FR");
		countryService.insert(countryFinish);
		

		City cityFinish= new City();
		cityFinish.setName("Paris");
		cityFinish.setTimezone(2.0);
		cityFinish.setCountry(countryFinish);
		cityService.insert(cityFinish);

		Airport airportFinish = new Airport();
		airportFinish.setName("Paris");
		airportFinish.setCodeIata("ADE");
		airportFinish.setCodeIcao("QWWE");
		airportFinish.setCity(cityFinish);
		airportFinish.setClassWeight(ClassWeight.MEDIUM);
		airportFinish.setCoordinatesX(5.3333);
		airportFinish.setCoordinatesY(1.3434);
		airportService.insert(airportFinish);
	
		
		FlightCatalog flightCatalog = new FlightCatalog();
		flightCatalog.setAirportStart(airportStart);
		flightCatalog.setAirportFinish(airportFinish);
		flightCatalog.setDistance(1000);

		flightCatalogService.insert(flightCatalog);

		Assert.assertFalse(flightCatalogService.checkInternatoinal(flightCatalog));
	}
	
}
