package by.bogdevich.training.airline.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.City;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class CitySrviceTest {

	@Inject
	private CityService cityService;

	@Inject
	private CountryService countryService;

	@Test
	public void testAdd() {
		City city = new City();
		city.setName("MySity");
		city.setTimezone(2.0);
		city.setCountry(countryService.get((long) 13));

		cityService.insert(city);
		City addCity = cityService.get(city.getId());

		List<City> list = cityService.getAll();
		
		Assert.assertNotNull(addCity);

		// cityService.delete(city.getId());

	}

}
