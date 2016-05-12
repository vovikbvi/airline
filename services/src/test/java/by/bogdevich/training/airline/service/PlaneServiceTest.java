package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.Plane;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class PlaneServiceTest extends AbstractTest{

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

	@Test
	public void testDeletPlane() {
		Plane plane = planeAdd();
		planeService.delete(plane.getId());

		Assert.assertNull(planeService.get(plane.getId()));
	}

}
