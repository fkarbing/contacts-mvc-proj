package se.fkarbing.spring.mvc.core.util;

import java.util.UUID;

import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.common.model.mock.MyModelMock;
import se.fkarbing.spring.mvc.core.util.MvcControllerUtil;

public class MvcControllerUtilTest {

	@Test
	public void testAll() {

		MyModel existingIntance = MyModelMock.existingIntance(UUID.randomUUID()
				.toString());
		System.out.println(MvcControllerUtil.resourcePath(existingIntance
				.getClass()));
		System.out.println(MvcControllerUtil
				.resourcePathWithId(existingIntance));
	}
}
