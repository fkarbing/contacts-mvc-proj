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
import se.fkarbing.spring.mvc.common.model.Location;
import se.fkarbing.spring.mvc.common.model.mock.LocationMock;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextCommonConfigRIAK.class)
@DirtiesContext
public class LocationRepositoryInMemoryTest {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	@Qualifier("locationRepository")
	private GenericRepositoryInMemory<Location> locationRepository;


	@Test
	public void testStore() {
		Location newIntance = LocationMock.newIntance();
		Location persistedModel = locationRepository.store(newIntance);
		org.junit.Assert.assertNotNull(persistedModel);
		org.junit.Assert.assertNotNull(persistedModel.id());
		org.junit.Assert.assertEquals(newIntance.name(), persistedModel.name());
	}


	@Test
	public void testFetch() {
		// Create
		Location newIntance = LocationMock.newIntance();
		Location persistedModel = locationRepository.store(newIntance);
		org.junit.Assert.assertNotNull(persistedModel);
		// Fetch
		Location fetchedModel = locationRepository.fetch(persistedModel.id());
		org.junit.Assert.assertNotNull(fetchedModel);
		org.junit.Assert.assertEquals(persistedModel, fetchedModel);
	}
}
