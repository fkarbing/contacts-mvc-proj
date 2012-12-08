package se.fkarbing.spring.mvc.core.repository;

import org.junit.Assert;
import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.common.model.mock.MyModelMock;

public class InMemoryModelIdManagerTest {

	private final InMemoryModelIdManager idManager = new InMemoryModelIdManager();

	/* testErrorIdInvalid */
	@Test(expected = IllegalArgumentException.class)
	public void testErrorIdInvalid() {
		idManager.validateId("INVALID ID");
	}

	/* testErrorIdNull */
	@Test(expected = NullPointerException.class)
	public void testErrorIdNull() {
		idManager.validateId(null);
	}

	/* setNewId */
	public void testSetNewId() {
		MyModel newIntance = MyModelMock.newIntance();
		idManager.setNewId(newIntance);
		Assert.assertTrue(newIntance != null);
		idManager.validateId(newIntance.id());
	}

}
