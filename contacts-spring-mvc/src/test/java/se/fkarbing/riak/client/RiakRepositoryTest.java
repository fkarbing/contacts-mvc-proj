package se.fkarbing.riak.client;

import java.util.List;
import java.util.UUID;

import junit.framework.Assert;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.MyModel;

import com.basho.riak.client.RiakException;

public class RiakRepositoryTest {

	String id = UUID.randomUUID().toString();
	MyModel _model = new MyModel(id, "name_" + System.currentTimeMillis());

	static RiakRepository<MyModel> repository;


	@BeforeClass
	public static void setUp() {
		repository = new RiakRepository<MyModel>(MyModel.class);
	}


	@After
	public void after() {
		_remove();
	}


	@Test
	public void testStore() throws RiakException {
		MyModel model = _store();
		Assert.assertNotNull(model);
	}


	@Test
	public void testFetch() throws RiakException {
		_store();
		MyModel model = repository.fetch(_model.id());
		Assert.assertNotNull(model);
		Assert.assertEquals(_model, model);

	}


	@Test
	public void testFetchAll() throws RiakException {
		_store();
		String _id = UUID.randomUUID().toString();
		_model = new MyModel(_id, "name_" + System.currentTimeMillis());
		_store();
		List<MyModel> fetchAll = repository.fetchAll();
		Assert.assertNotNull(fetchAll);
		Assert.assertTrue(2 == fetchAll.size());

		repository.removeAll();
	}


	@Test
	public void testRemove() throws RiakException {
		MyModel model = repository.remove(_model.id());
		Assert.assertNull(model);

		model = repository.fetch(_model.id());
		Assert.assertNull(model);

		List<MyModel> fetchAll = repository.fetchAll();
		Assert.assertNotNull(fetchAll);
		Assert.assertTrue(0 == fetchAll.size());

	}


	@Test
	public void testRemoveAll() throws RiakException {
		boolean result = repository.removeAll();
		Assert.assertTrue(result);

		MyModel model = repository.fetch(_model.id());
		Assert.assertNull(model);
	}


	/*
	 * @Test public void testFetchAll() throws RiakException { MyModel model =
	 * repository.fetch(model); Assert.assertNotNull(model) }
	 */

	/*
	 * @Test public void testFetchAll() throws RiakException {
	 * 
	 * MyModel store = repository.fetchAll(); System.out.println(store); }
	 */

	private MyModel _store() {
		MyModel store = repository.store(_model);
		System.out.println(store);
		return store;
	}


	private MyModel _remove() {
		MyModel remove = repository.remove(_model.id());
		System.out.println(remove);
		return remove;
	}
}
