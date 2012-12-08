package se.fkarbing.spring.mvc.core.repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import se.fkarbing.core.model.GenericModel;
import se.fkarbing.core.repository.IRepository;
import se.fkarbing.spring.mvc.common.model.MyModel;

import com.google.common.collect.Lists;

@Repository
public class GenericRepositoryInMemory<T extends GenericModel> implements IRepository<T> {

	private final Logger LOG = LoggerFactory.getLogger(GenericRepositoryInMemory.class);

	// Since parent class is injected as singleton, no need for Static
	private final ConcurrentHashMap<String, T> repositoryInstance = new ConcurrentHashMap<>();

	@Autowired
	ModelIdManager idManager;


	/*
	 * (non-Javadoc)
	 * 
	 * @see se.fkarbing.core.repository.IRepository#fetch(java.lang.String)
	 */
	@Override
	public T fetch(@NotNull final String id) {

		idManager.validateId(id);
		final T model = repositoryInstance.get(id);
		LOG.info("Repository: fetch() for id: " + id + " found model: "
		        + (model != null ? model : "DONT EXIST!"));
		return model;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see se.fkarbing.core.repository.IRepository#fetchAll()
	 */

	@Override
	public List<T> fetchAll() {

		Collection<T> values = repositoryInstance.values();
		final List<T> models = Lists.newArrayList(values);
		LOG.info("fetchAll() returning #models: " + models.size());
		// Collections.sort(models);
		return models;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see se.fkarbing.core.repository.IRepository#store(T)
	 */
	@Override
	public T store(T model) {

		if (model.id() == null) {
			idManager.setNewId(model);
		}
		repositoryInstance.put(model.id(), model);
		LOG.info("store(), new size of storage: " + repositoryInstance.size());
		return model;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see se.fkarbing.core.repository.IRepository#remove(java.lang.String)
	 */
	@Override
	public T remove(String id) {

		idManager.validateId(id);
		T removedModel = repositoryInstance.remove(id);
		LOG.info("remove(), new size of storage: " + repositoryInstance.size());
		return removedModel;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see se.fkarbing.core.repository.IRepository#removeAll()
	 */
	@Override
	public boolean removeAll() {

		repositoryInstance.clear();
		LOG.info("remove(), new size of storage: " + repositoryInstance.size());
		return true;
	}


	/* Local (q&d) testing */
	public static void main(String[] args) {

		MyModel newIntance = new MyModel(null, "new MyModel..");
		GenericRepositoryInMemory<MyModel> myModelRepository = new GenericRepositoryInMemory<>();
		myModelRepository.idManager = new InMemoryModelIdManager();
		myModelRepository.store(newIntance);
	}
}
