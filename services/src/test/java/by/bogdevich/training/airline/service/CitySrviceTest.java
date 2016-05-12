package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.City;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class CitySrviceTest extends AbstractTest{

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

	@Test
	public void testDeletCity() {
		City city = cityAdd();
		cityService.delete(city.getId());

		Assert.assertNull(cityService.get(city.getId()));
	}

}
