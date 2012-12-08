package se.fkarbing.spring.mvc.common.model.mock;

import se.fkarbing.spring.mvc.common.model.MyModel;

public final class MyModelMock {

	/** Generates a new instance, setting the name to: xxx: (1:1000) */
	public static MyModel newIntance() {
		return new MyModel(null, "mock model: "
				+ ((int) (1000 * Math.random())));
	}

	/** Generates a new instance, setting the name to: xxx: id */
	public static MyModel existingIntance(String id) {
		return new MyModel(id, "mock model: " + id);
	}
}