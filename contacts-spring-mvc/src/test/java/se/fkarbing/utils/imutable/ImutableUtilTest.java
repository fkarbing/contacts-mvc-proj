package se.fkarbing.utils.imutable;

import java.lang.reflect.Field;

import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.common.model.mock.MyModelMock;

public class ImutableUtilTest {

	@Test
	public void testSetProtectedFields() throws Exception {
		MyModel newInstance = MyModelMock.newIntance();

		// Test 1
		Field idField = newInstance.getClass().getSuperclass().getDeclaredField("id");
		ImutableUtil.setNonPublicField(newInstance, idField, "new injected value");
		System.out.println(newInstance);

		// Test 2
		ImutableUtil.setParentNonPublicField(newInstance, "id", "Another new value");
		System.out.println(newInstance);
	}

}
