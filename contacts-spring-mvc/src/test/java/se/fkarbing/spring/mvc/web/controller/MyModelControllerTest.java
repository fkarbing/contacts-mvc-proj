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

import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.web.MyModelContextWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = MyModelContextWebConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class MyModelControllerTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MyModelController myModelController;

	private final MyModel defMyModel = new MyModel(null, "Fredrik Karbing");
	private MockHttpServletRequest servletRequest;


	@Before
	public void before() {
		// Always create 1 MyModel to avoid repeated "create user" in test
		servletRequest = new MockHttpServletRequest(null, "/mymodels");
		myModelController.create(defMyModel, servletRequest);
	}


	/*------------- TEST's --------------*/

	@Test
	public void testSetUp() {
		Assert.assertNotNull(this.myModelController);
	}


	/*
	 * TEST: create update fetch fetchAll remove removeAll
	 */

	@Test()
	public void testCreate() {
		MyModel newMyModel = new MyModel(null, "A new Fredrik Karbing");
		final ResponseEntity<MyModel> responseEntity = myModelController.create(newMyModel,
		        servletRequest);
		assertNotNull(responseEntity);
		assertEquals(newMyModel, responseEntity.getBody());
	}


	@Test()
	public void testFetch() {
		MyModel fetch = myModelController.fetch(defMyModel.id());
		assertNotNull(fetch);

	}


	@Test()
	public void testFetchAll() {
		List<MyModel> fetches = myModelController.fetchAll();
		assertNotNull(fetches);
	}


	@Test()
	public void testRemove() {
		MyModel removed = myModelController.remove(defMyModel.id());
		assertEquals(null, removed);
	}


	@Test()
	public void testRemoveAll() {
		boolean result = myModelController.removeAll();
		assertEquals(true, result);
	}

}