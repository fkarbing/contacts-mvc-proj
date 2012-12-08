package se.fkarbing.riak.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import se.fkarbing.core.model.GenericModel;
import se.fkarbing.core.repository.IRepository;
import se.fkarbing.spring.mvc.common.model.Location;
import se.fkarbing.spring.mvc.core.repository.ModelIdManager;
import se.fkarbing.spring.mvc.core.repository.RiakModelIdManager;

import com.basho.riak.client.IRiakClient;
import com.basho.riak.client.RiakException;
import com.basho.riak.client.RiakFactory;
import com.basho.riak.client.RiakRetryFailedException;
import com.basho.riak.client.bucket.Bucket;
import com.basho.riak.client.operations.DeleteObject;
import com.basho.riak.client.operations.FetchObject;
import com.basho.riak.client.operations.StoreObject;

@Repository
public class RiakRepository<T extends GenericModel> implements IRepository<T> {

	private static final Logger LOG = LoggerFactory.getLogger(RiakRepository.class);

	// TODO: Since parent class is injected as singleton, inject this to and
	// remove static pattern

	// -@Autowired
	private static IRiakClient riakClient = null;
	// -@Autowired
	private static ModelIdManager riakIdManager;
	private final Class<T> clazz;
	private final String bucketName;


	public RiakRepository(Class<T> clazz) {

		this.clazz = clazz;
		this.bucketName = getBucketName(clazz);
		LOG.info("bucketName: " + bucketName);

		try {
			if (riakClient == null) {
				riakClient = RiakFactory.httpClient("http://127.0.0.1:8098/riak");
				LOG.info("riakClient init.");
			}
			// Verify
			Bucket myBucket = riakClient.createBucket("myBucket").execute();
			System.out.println(myBucket.getName());
			myBucket = null;

			if (riakIdManager == null) {
				riakIdManager = new RiakModelIdManager();
			}

		} catch (RiakException e) {
			LOG.error(e.getMessage(), e);
			e.printStackTrace();
		}
		LOG.info("<<< RiakRepository()");
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see se.fkarbing.core.repository.IRepository#fetch(java.lang.String)
	 */

	@Override
	public T fetch(final String id) {
		LOG.debug(">>> fetch...");
		try {
			riakIdManager.validateId(id);
			Bucket myBucket = riakClient.createBucket("myBucket").execute();
			FetchObject<T> fetchObject = myBucket.fetch(id, clazz);
			T fetchedModel = fetchObject.execute();
			LOG.info("fetchedModel: " + (fetchedModel != null ? fetchedModel.toString() : "null"));
			return fetchedModel;
		} catch (RiakRetryFailedException e) {
			LOG.info(e.getMessage(), e);
			return null;
		}

	}


	@Override
	public List<T> fetchAll() {
		LOG.debug(">>> fetchAll...");
		Bucket myBucket;
		ArrayList<T> list = new ArrayList<T>();
		try {
			myBucket = riakClient.createBucket(bucketName).execute();

			Iterable<String> keys = myBucket.keys();

			for (String key : keys) {
				if (key != null) {
					T fetch = this.fetch(key);
					if (fetch != null)
						list.add(fetch);
				}
			}
			LOG.info("fetchAll, #items: " + list.size());
			return list;
		} catch (Exception e) {
			LOG.info(e.getMessage(), e);
			return list;
		}

	}


	@Override
	public T store(T model) {
		LOG.debug(">>> store...");
		Bucket myBucket;
		try {
			if (model.id() == null) {
				riakIdManager.setNewId(model);
			}

			myBucket = riakClient.createBucket(bucketName).execute();

			StoreObject<T> storeObject = myBucket.store(model);
			T storedModel = storeObject.returnBody(true).execute();
			LOG.info("storedModel: " + storedModel.toString());
			return storedModel;
		} catch (RiakRetryFailedException e) {
			LOG.info(e.getMessage(), e);
			throw new IllegalStateException(e);
		}

	}


	@Override
	public T remove(String id) {
		LOG.debug(">>> remove...");
		Bucket myBucket;
		try {
			riakIdManager.validateId(id);
			myBucket = riakClient.createBucket(bucketName).execute();

			DeleteObject deleteObject = myBucket.delete(id);
			deleteObject.fetchBeforeDelete(true).execute();
			LOG.info("removed, id: " + id);

		} catch (Exception e) {
			LOG.info(e.getMessage(), e);
			return null;
		}
		return null;
	}


	@Override
	public boolean removeAll() {
		LOG.debug(">>> removeAll...");
		Bucket myBucket;
		try {
			myBucket = riakClient.createBucket(bucketName).execute();

			Iterable<String> keys = myBucket.keys();
			for (String key : keys) {
				this.remove(key);
			}
			LOG.info("removeAll done.");
		} catch (Exception e) {
			LOG.info(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
		return true;
	}


	private String getBucketName(Class<T> clazz) {
		return clazz.getSimpleName().toLowerCase() + "s";
	}


	public static void main(String[] args) throws RiakException {

		try {
			// RiakFactory.httpClient("http://127.0.0.1:8098/riak"); // or
			// Bucket myBucket = riakClient.createBucket("myBucket").execute();

			RiakRepository<Location> repository = new RiakRepository<>(Location.class);
			List<Location> fetchAll = repository.fetchAll();
			System.out.println("fetchAll: size: " + fetchAll.size());
			Bucket myBucket = riakClient.createBucket("myBucket").execute();

			String s = "asdasd";
			System.out.println(myBucket.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
