package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
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

}
