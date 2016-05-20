package by.bogdevich.training.airline.service;

import java.time.LocalDateTime;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.bogdevich.training.airline.datamodel.Airport;
import by.bogdevich.training.airline.datamodel.City;
import by.bogdevich.training.airline.datamodel.Country;
import by.bogdevich.training.airline.datamodel.Flight;
import by.bogdevich.training.airline.datamodel.FlightCatalog;
import by.bogdevich.training.airline.datamodel.ManufacturedPlane;
import by.bogdevich.training.airline.datamodel.ModelPlane;
import by.bogdevich.training.airline.datamodel.Plane;
import by.bogdevich.training.airline.datamodel.Price;
import by.bogdevich.training.airline.datamodel.Ticket;
import by.bogdevich.training.airline.datamodel.UserProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class InsertAllTable extends AbstractTest {

	// test country

	@Test
	public void testAddCountry() {
		Assert.assertNotNull(countryAdd());
	}

	@Test
	public void testUpdateCountry() {
		String updatedField = "updatedName";
		Country country = countryAdd();
		country.setName(updatedField);
		countryService.update(country);

		Assert.assertEquals(updatedField, countryService.get(country.getId()).getName());
	}

	//@Test
	public void testDeletCountry() {
		Country country = countryAdd();
		countryService.delet(country.getId());

		Assert.assertNull(countryService.get(country.getId()));
	}

	// end test country

	// test city

	@Test
	public void testAddCity() {

		Assert.assertNotNull(cityAdd());

	}

	@Test
	public void testUpdateCity() {
		String updatedField = "updatedName";
		City city = cityAdd();
		city.setName(updatedField);
		cityService.update(city);

		Assert.assertEquals(updatedField, cityService.get(city.getId()).getName());
	}

	//@Test
	public void testDeletCity() {
		City city = cityAdd();
		cityService.delete(city.getId());

		Assert.assertNull(cityService.get(city.getId()));
	}

	// end test city

	// test airport
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

	//@Test
	public void testDeletAirport() {
		Airport airport = airportAdd();
		airportService.delete(airport.getId());

		Assert.assertNull(airportService.get(airport.getId()));
	}

	// end test airport

	// test flight catalog

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

	//@Test
	public void testDeletFlightCatalog() {
		FlightCatalog flightCatalog = flightCatalogAdd();
		flightCatalogService.delete(flightCatalog.getId());

		Assert.assertNull(flightCatalogService.get(flightCatalog.getId()));
	}

	// end test flight catalog

	// test Manufactured Plane

	@Test
	public void testAddManufacturedPlane() {
		Assert.assertNotNull(manufacturedPlaneAdd());

	}

	@Test
	public void testUpdateManufacturedPlane() {
		String updatedField = "UpdateField";
		ManufacturedPlane manufacturedPlane = manufacturedPlaneAdd();
		manufacturedPlane.setName(updatedField);
		manufacturedPlameService.update(manufacturedPlane);

		Assert.assertEquals(updatedField, manufacturedPlameService.get(manufacturedPlane.getId()).getName());
	}

	//@Test
	public void testDeletManufacturedPlane() {
		ManufacturedPlane manufacturedPlane = manufacturedPlaneAdd();
		manufacturedPlameService.delete(manufacturedPlane.getId());

		Assert.assertNull(manufacturedPlameService.get(manufacturedPlane.getId()));
	}

	// end test Manufactured Plane

	// test model plane

	@Test
	public void testAddModelPlane() {
		Assert.assertNotNull(modelPlaneAdd());
	}

	@Test
	public void testUpdateModelPlane() {
		int updatedField = 62;
		ModelPlane modelPlane = modelPlaneAdd();
		modelPlane.setColPassangersBuisnes(updatedField);
		modelPlaneService.update(modelPlane);

		Assert.assertEquals((long) updatedField,
				(long) modelPlaneService.get(modelPlane.getId()).getColPassangersBuisnes());
	}

	//@Test
	public void testDeletModelPlane() {
		ModelPlane modelPlane = modelPlaneAdd();
		modelPlaneService.delete(modelPlane.getId());

		Assert.assertNull(modelPlaneService.get(modelPlane.getId()));
	}

	// end test model plane

	// test plane

	@Test
	public void testAddPlane() {
		Assert.assertNotNull(planeAdd());
	}

	@Test
	public void testUpdatePlane() {
		String updatedField = "new field";
		Plane plane = planeAdd();
		plane.setBortNumber(updatedField);
		planeService.update(plane);

		Assert.assertEquals(updatedField, planeService.get(plane.getId()).getBortNumber());
	}

	//@Test
	public void testDeletPlane() {
		Plane plane = planeAdd();
		planeService.delete(plane.getId());

		Assert.assertNull(planeService.get(plane.getId()));
	}

	// end test plane

	// Test flieght
	@Test
	public void testAddFlight() {
		Flight flight = flightAdd();

		Flight flightTest = flightService.getFullFlieght(flight);

		ModelPlane model = flightTest.getPlane().getModelPlane();

		Assert.assertNotNull(flightAdd());
	}

	@Test
	public void testUpdateFlieght() {
		LocalDateTime updatedField = LocalDateTime.now();
		updatedField.plusDays(1);
		Flight flight = flightAdd();
		flight.setDepartureTime(updatedField);
		flightService.update(flight);

		Assert.assertTrue(updatedField.equals(flightService.get(flight.getId()).getDepartureTime()));
	}

	//@Test
	public void testDeletFlight() {
		Flight flight = flightAdd();
		flightService.delete(flight.getId());

		Assert.assertNull(flightService.get(flight.getId()));
	}

	// end Test flieght

	// test user profile

	@Test
	public void testAddUserProfile() {

		Assert.assertNotNull(userProfileAdd());

	}

	@Test
	public void testUpdateUserProfile() {
		String updatedField = "new field";
		UserProfile userProfile = userProfileAdd();
		userProfile.setLastName(updatedField);
		userProfileService.update(userProfile);

		Assert.assertEquals(updatedField, userProfileService.get(userProfile.getId()).getLastName());
	}

	//@Test
	public void testDeletUserProfile() {
		UserProfile userProfile = userProfileAdd();
		userProfileService.delete(userProfile.getId());

		Assert.assertNull(userProfileService.get(userProfile.getId()));
	}

	// end test user profile

	// test ticket
	@Test
	public void testAddTicket() {
		Assert.assertNotNull(ticketAdd());
	}

	@Test
	public void testUpdateTicket() {
		Integer updatedField = 4;
		Ticket ticket = ticketAdd();
		ticket.setNumberSeats(updatedField);
		ticketService.update(ticket);

		Assert.assertEquals((long) updatedField, (long) ticketService.get(ticket.getId()).getNumberSeats());
	}

	//@Test
	public void testDeletTicket() {
		Ticket ticket = ticketAdd();
		ticketService.delete(ticket.getId());

		Assert.assertNull(ticketService.get(ticket.getId()));
	}

	// test ticket

	// test price
	@Test
	public void testAddPrice() {
		Assert.assertNotNull(priceAdd());
	}

	@Test
	public void testUpdatePrice() {
		Double updatedField = 0.2;
		Price price = priceAdd();
		price.setBasicPrice(updatedField);
		priceService.update(price);

		Assert.assertEquals(updatedField, priceService.get(price.getId()).getBasicPrice());
	}

	//@Test
	public void testDeletPrice() {
		Price price = priceAdd();
		priceService.delete(price.getId());

		Assert.assertNull(priceService.get(price.getId()));
	}

	// end test price
	
	
	//@After
	public void dellAll(){
		deletAllData();
	}
	
}
