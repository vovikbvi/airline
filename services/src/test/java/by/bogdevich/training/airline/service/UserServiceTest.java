package by.bogdevich.training.airline.service;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import by.bogdevich.training.airline.dataaccess.CountryDao;
import by.bogdevich.training.airline.dataaccess.PriceDao;
import by.bogdevich.training.airline.dataaccess.impl.AbstractDaoImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class UserServiceTest {

	@Inject
	private CountryService countryService;
	
	@Inject
	private CountryDao countryDao;

	
	@Inject
	private PriceDao priceDao;
	
	@Test
	public void test() {
		Assert.assertNotNull(countryService);
	}
	
	
    @Test
    public void testEntityManagerInitialization() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        Field f = AbstractDaoImpl.class.getDeclaredField("entityManager");
        f.setAccessible(true);
        EntityManager em = (EntityManager) f.get(countryDao);

        Assert.assertNotNull(em);
    }


}
