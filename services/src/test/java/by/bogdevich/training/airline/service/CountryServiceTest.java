package by.bogdevich.training.airline.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import by.bogdevich.training.airline.datamodel.Country;
import org.junit.Assert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class CountryServiceTest extends AbstractTest{

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

	@Test
	public void testDeletCountry() {
		Country country = countryAdd();
		countryService.delet(country.getId());

		Assert.assertNull(countryService.get(country.getId()));
	}

}
