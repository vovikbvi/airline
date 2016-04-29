package by.bogdevich.training.airline.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Country;

import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:resources/service-context-test.xml" })
public class CountryServiceTest {

	@Inject
	CountryService countryService;

	@Test
	public void testCountry() {
		Country country = new Country();

		country.setName("FR");

		countryService.insert(country);

		Country countryAdd = countryService.get(country.getId());

		Assert.assertNotNull(countryAdd);

		String upName = "DE";
		country.setName(upName);
		countryService.update(country);
		Assert.assertEquals(upName, countryService.get(country.getId()).getName());

		countryService.delet(country.getId());
		Assert.assertNull(countryService.get(country.getId()));
	
	}

}
