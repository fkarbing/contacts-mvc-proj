package se.fkarbing.spring.mvc.common.model.mock;

import se.fkarbing.spring.mvc.common.model.Location;

public final class LocationMock {

	/** Generates a new instance, setting the name to: xxx: (1:1000) */
	public static Location newIntance() {
		return new Location(null, "mock model: " + ((int) (1000 * Math.random())));
	}


	/** Generates a new instance, setting the name to: xxx: id */
	public static Location existingIntance(String id) {
		return new Location(id, "mock model: " + id);
	}
}