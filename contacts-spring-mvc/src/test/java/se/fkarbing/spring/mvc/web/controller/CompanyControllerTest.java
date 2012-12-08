package se.fkarbing.spring.mvc.web.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import se.fkarbing.spring.mvc.common.model.Company;
import se.fkarbing.spring.mvc.common.model.mock.CompanyMock;
import se.fkarbing.spring.mvc.core.util.MvcControllerUtil;
import se.fkarbing.spring.mvc.web.AppContextWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextWebConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CompanyControllerTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CompanyController companyController;

	private final Company defCompany = CompanyMock.newIntance();
	private MockHttpServletRequest servletRequest;


	@Before
	public void before() {
		// Always create 1 Company to avoid repeated "create user" in test
		servletRequest = new MockHttpServletRequest(null,
		        MvcControllerUtil.resourcePath(Company.class));
		companyController.create(defCompany, servletRequest);
	}


	/*------------- TEST's --------------*/

	@Test
	public void testSetUp() {
		Assert.assertNotNull(this.companyController);
	}


	/*
	 * TEST: create update fetch fetchAll remove removeAll
	 */

	@Test()
	public void testCreate() {
		Company newCompany = CompanyMock.newIntance();
		final ResponseEntity<Company> responseEntity = companyController.create(newCompany,
		        servletRequest);
		assertNotNull(responseEntity);
		assertEquals(newCompany, responseEntity.getBody());
	}

}