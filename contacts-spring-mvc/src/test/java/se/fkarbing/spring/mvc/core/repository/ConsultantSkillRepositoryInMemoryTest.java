package se.fkarbing.spring.mvc.core.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.fkarbing.spring.mvc.common.AppContextCommonConfigRIAK;
import se.fkarbing.spring.mvc.common.model.ConsultantSkill;
import se.fkarbing.spring.mvc.common.model.mock.ConsultantSkillMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextCommonConfigRIAK.class)
@DirtiesContext
public class ConsultantSkillRepositoryInMemoryTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("consultantSkillRepository")
	private GenericRepositoryInMemory<ConsultantSkill> consultantSkillRepository;


	@Test
	public void testStore() {
		ConsultantSkill newIntance = ConsultantSkillMock.newIntance();
		ConsultantSkill persistedModel = consultantSkillRepository.store(newIntance);
		org.junit.Assert.assertNotNull(persistedModel);
		org.junit.Assert.assertNotNull(persistedModel.id());
		org.junit.Assert.assertEquals(newIntance.name(), persistedModel.name());
	}


	@Test
	public void testFetch() {
		// Create
		ConsultantSkill newIntance = ConsultantSkillMock.newIntance();
		ConsultantSkill persistedModel = consultantSkillRepository.store(newIntance);
		org.junit.Assert.assertNotNull(persistedModel);
		// Fetch
		ConsultantSkill fetchedModel = consultantSkillRepository.fetch(persistedModel.id());
		org.junit.Assert.assertNotNull(fetchedModel);
		org.junit.Assert.assertEquals(persistedModel, fetchedModel);
	}
}
