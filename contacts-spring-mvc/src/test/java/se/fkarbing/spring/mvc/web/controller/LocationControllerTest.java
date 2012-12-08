package se.fkarbing.spring.mvc.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.fkarbing.spring.mvc.common.model.Location;
import se.fkarbing.spring.mvc.web.AppContextWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextWebConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class LocationControllerTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private LocationController locationController;

	private final Location defLocation = new Location(null, "Fredrik Karbing");
	private MockHttpServletRequest servletRequest;


	@Before
	public void before() {
		// Always create 1 Location to avoid repeated "create location" in test
		servletRequest = new MockHttpServletRequest(null, "/locations");
		locationController.create(defLocation, servletRequest);
	}


	/*------------- TEST's --------------*/

	@Test
	public void testSetUp() {
		Assert.assertNotNull(this.locationController);
	}


	@Test()
	public void testCreate() {
		Location newLocation = new Location(null, "A new Fredrik Karbing");
		final ResponseEntity<Location> responseEntity = locationController.create(newLocation,
		        servletRequest);
		assertNotNull(responseEntity);
		assertEquals(newLocation, responseEntity.getBody());
	}


	@Test()
	public void testFetch() {
		Location fetch = locationController.fetch(defLocation.id());
		assertNotNull(fetch);

	}


	@Test()
	public void testFetchAll() {
		List<Location> fetches = locationController.fetchAll();
		assertNotNull(fetches);
	}


	@Test()
	public void testRemove() {
		Location removed = locationController.remove(defLocation.id());
		assertEquals(null, removed);
	}


	@Test()
	public void testRemoveAll() {
		boolean result = locationController.removeAll();
		assertEquals(true, result);
	}

}