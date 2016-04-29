package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Country;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class InsertAllTableTest {

	private Country country = new Country();

	@Inject
	CountryService countryService;

	@Test
	public void testCountry() {

		country.setName("FR");

		countryService.insert(country);

		Country countryAdd = countryService.get(country.getId());

		Assert.assertNotNull(countryAdd);
	}

}
