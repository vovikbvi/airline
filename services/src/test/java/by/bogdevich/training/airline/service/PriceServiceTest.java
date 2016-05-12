package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Price;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class PriceServiceTest extends AbstractTest{

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

	@Test
	public void testDeletPrice() {
		Price price = priceAdd();
		priceService.delete(price.getId());

		Assert.assertNull(priceService.get(price.getId()));
	}

}
