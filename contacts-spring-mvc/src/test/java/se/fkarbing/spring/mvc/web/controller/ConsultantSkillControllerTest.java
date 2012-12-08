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

import se.fkarbing.spring.mvc.common.model.ConsultantSkill;
import se.fkarbing.spring.mvc.web.AppContextWebConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextWebConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class ConsultantSkillControllerTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ConsultantSkillController consultantSkillController;

	private final ConsultantSkill defConsultantSkill = new ConsultantSkill(null, "Fredrik Karbing");
	private MockHttpServletRequest servletRequest;


	@Before
	public void before() {
		// Always create 1 ConsultantSkill to avoid repeated "create location"
		// in test
		servletRequest = new MockHttpServletRequest(null, "/locations");
		consultantSkillController.create(defConsultantSkill, servletRequest);
	}


	/*------------- TEST's --------------*/

	@Test
	public void testSetUp() {
		Assert.assertNotNull(this.consultantSkillController);
	}


	/*
	 * TEST: create update fetch fetchAll remove removeAll
	 */

	@Test()
	public void testCreate() {
		ConsultantSkill newConsultantSkill = new ConsultantSkill(null, "A new Fredrik Karbing");
		final ResponseEntity<ConsultantSkill> responseEntity = consultantSkillController.create(
		        newConsultantSkill, servletRequest);
		assertNotNull(responseEntity);
		assertEquals(newConsultantSkill, responseEntity.getBody());
	}

}