package se.fkarbing.spring.mvc.common.model.mock;

import se.fkarbing.spring.mvc.common.model.Company;

public final class CompanyMock {

	static final String address = "Västra Järnvägsgatan 21 111 64 STOCKHOLM";
	static final double[] latLng = { 59.33230529999999, 18.05283570 };
	static final String latlng = "59.33230529999999, 18.05283570";


	/** Generates a new instance, setting the name to: xxx: (1:1000) */
	public static Company newIntance() {
		return new Company(null, "mock model: " + ((int) (1000 * Math.random())), address, latlng);
	}


	/*
	 * public static Company newIntance() { return new Company(null,
	 * "mock model: " + ((int) (1000 * Math.random())), address, latLng); }
	 */

	/** Generates a new instance, setting the name to: xxx: id */
	public static Company existingIntance(String id) {
		return new Company(id, "mock model: " + id, address, latlng);
	}
	/*
	 * public static Company existingIntance(String id) { return new Company(id,
	 * "mock model: " + id, address, latLng); }
	 */
}