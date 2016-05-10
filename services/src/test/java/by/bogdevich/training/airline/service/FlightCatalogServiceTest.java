package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.hibernate.cfg.ExtendsQueueEntry;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.bogdevich.training.airline.datamodel.FlightCatalog;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class FlightCatalogServiceTest extends AbstractTest{

	@Inject
	FlightCatalogService flightCatalogService;

	@Test
	public void testFlightCatalog() {
		FlightCatalog flightCatalog = new FlightCatalog();
		flightCatalog.setAirportStart(airportAdd());
		flightCatalog.setAirportFinish(airportAdd());
		flightCatalog.setDistance(1000);

		flightCatalogService.insert(flightCatalog);

		FlightCatalog flightCatalogAdd = flightCatalogService.get(flightCatalog.getId());

		Assert.assertNotNull(flightCatalogAdd);
	}

}
