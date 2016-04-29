package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.dataaccess.AirportDao;
import by.bogdevich.training.airline.dataaccess.PlaneDao;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UsePlaneTest {
	
	@Inject
	PlaneDao planeDao;

	@Inject
	AirportDao airportDao;
	
	@Inject
	FlightService flightService;
	
	@Test
	public void usePlaneTest(){
		
		Plane plane = new Plane();
		Airport airport = new Airport();
		ModelPlane modelPlane = new ModelPlane();
		FlightCatalog catalog = new FlightCatalog();
		
		Flight flight = new Flight();
		
		modelPlane.setClassWeight(ClassWeight.MEDIUM);
		plane.setModelPlane(modelPlane);
		airport.setName("dsfsds");
		airport.setCodeIata("asa");
		airport.setClassWeight(ClassWeight.HEAVY);
		catalog.setAirportFinish(airport);

		flight.setFlightsCatalog(catalog);
		flight.setPlane(plane);
		
		flightService.insert(flight);
		
		Flight flightAdd = flightService.get(flight.getId());
		Assert.assertNotNull(flightAdd);
		
	}
}
