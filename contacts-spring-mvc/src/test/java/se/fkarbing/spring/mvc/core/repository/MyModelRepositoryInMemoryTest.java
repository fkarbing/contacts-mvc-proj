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
import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.common.model.mock.MyModelMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextCommonConfigRIAK.class)
@DirtiesContext
public class MyModelRepositoryInMemoryTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("myModelRepository")
	private GenericRepositoryInMemory<MyModel> myModelRepository;


	@Test
	public void testStore() {
		MyModel newIntance = MyModelMock.newIntance();
		MyModel persistedModel = myModelRepository.store(newIntance);
		org.junit.Assert.assertNotNull(persistedModel);
		org.junit.Assert.assertNotNull(persistedModel.id());
		org.junit.Assert.assertEquals(newIntance.name(), persistedModel.name());
	}


	@Test
	public void testFetch() {
		// Create
		MyModel newIntance = MyModelMock.newIntance();
		MyModel persistedModel = myModelRepository.store(newIntance);
		org.junit.Assert.assertNotNull(persistedModel);
		// Fetch
		MyModel fetchedModel = myModelRepository.fetch(persistedModel.id());
		org.junit.Assert.assertNotNull(fetchedModel);
		org.junit.Assert.assertEquals(persistedModel, fetchedModel);
	}
}
