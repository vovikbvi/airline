package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.ClassWeight;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class AirportServiceTest {

	@Inject
	AirportService airportService;

	@Inject
	CityService cityService;

	@Test
	public void testAirport() {
		Airport airport = new Airport();

		airport.setName("Minsk");
		airport.setCodeIata("AAA");
		airport.setCodeIcao("QWEE");
		// airport.setCity(cityAdd());
		airport.setClassWeight(ClassWeight.MEDIUM);
		airport.setCoordinatesX(2.3333);
		airport.setCoordinatesY(2.3434);

		airportService.insert(airport);

		Airport airportAdd = airportService.get(airport.getId());
		Assert.assertNotNull(airportAdd);

	}
}
