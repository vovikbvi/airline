package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class AirportServiceTest {

	
	@Inject
	AirportService airportService;
	
	@Inject
	CityService cityService;
	
	@Test
	public void testAirport(){
	Airport airport = new Airport();
	
	airport.setName("MinstAiro");
	airport.setCodeIata("mia");
	airport.setCity(cityService.get((long) 5));
	
	airportService.insert(airport);
	
		Airport addAirport = airportService.get((long) 1);
		Assert.assertNotNull(addAirport);
	
	}
}
