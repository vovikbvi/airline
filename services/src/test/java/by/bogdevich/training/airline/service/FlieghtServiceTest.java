package by.bogdevich.training.airline.service;


import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.ClassWeight;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.ModelPlane;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })

public class FlieghtServiceTest extends AbstractTest{
	
	@Test
	public void testAddFlight() {
		Flight flight = flightAdd();

		Flight flightTest = flightService.getFlieghtWithFetch(flight);

		ModelPlane model = flightTest.getPlane().getModelPlane();

		Assert.assertNotNull(flightAdd());
	}

	@Test
	public void testUpdateFlieght() {
		Date updatedField = new Date();
		//updatedField.getDay(1);
		Flight flight = flightAdd();
		flight.setDepartureTime(updatedField);
		flightService.update(flight);

		Assert.assertTrue(updatedField.equals(flightService.get(flight.getId()).getDepartureTime()));
	}

	@Test
	public void testDeletFlight() {
		Flight flight = flightAdd();
		flightService.delete(flight.getId());

		Assert.assertNull(flightService.get(flight.getId()));
	}

	
	@Test
	public void classWeightTest(){
		boolean result = flightService.checkClassWeight(flightAdd());
		Assert.assertTrue(result);
	}
}
