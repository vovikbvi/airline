package by.bogdevich.training.airline.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import by.bogdevich.training.airline.datamodel.ModelPlane;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:service-context-test.xml" })
public class ModelPlaneServiceTest extends AbstractTest{

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

	@Test
	public void testDeletModelPlane() {
		ModelPlane modelPlane = modelPlaneAdd();
		modelPlaneService.delete(modelPlane.getId());

		Assert.assertNull(modelPlaneService.get(modelPlane.getId()));
	}

}
