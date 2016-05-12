package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.ManufacturedPlane;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class ManufacturedPlainServiceTest extends AbstractTest{

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

	@Test
	public void testDeletManufacturedPlane() {
		ManufacturedPlane manufacturedPlane = manufacturedPlaneAdd();
		manufacturedPlameService.delete(manufacturedPlane.getId());

		Assert.assertNull(manufacturedPlameService.get(manufacturedPlane.getId()));
	}

}
