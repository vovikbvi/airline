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
public class AirportServiceTest extends AbstractTest {

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

}
