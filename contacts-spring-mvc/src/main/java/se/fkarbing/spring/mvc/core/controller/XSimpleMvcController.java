package se.fkarbing.spring.mvc.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import se.fkarbing.core.model.GenericModel;
import se.fkarbing.core.repository.IRepository;
import se.fkarbing.spring.mvc.core.util.MvcControllerUtil;

public abstract class XSimpleMvcController<T extends GenericModel> {

	protected final Logger LOG = LoggerFactory.getLogger(this.getClass());


	protected ResponseEntity<T> handleCreate(T model, IRepository<T> repository,
	        HttpServletRequest request) {
		LOG.info(">>> create()... model: " + model);
		LOG.info("req url: " + request.getRequestURL());
		if (model.id() != null)
			throw new IllegalStateException("create(): model.id() != null");
		T createdModel = repository.store(model);
		LOG.info("<<< create(), id: " + createdModel.id());
		// Location = /s/{id} & SC = Created
		return MvcControllerUtil.newResponseCreated(model, request);
	}


	protected ResponseEntity<T> handleUpdate(T model, IRepository<T> repository) {
		LOG.info(">>> update()... model: " + model);
		if (model.id() == null)
			throw new IllegalStateException("update(): model.id() == null");
		T updatedModel = repository.store(model);
		LOG.info("<<< update(), id: " + updatedModel.id());
		return new ResponseEntity<T>(updatedModel, HttpStatus.OK);
	}


	protected T handleFetch(String id, IRepository<T> repository) {
		LOG.debug(">>> fetch()... ");
		T fetchedModel = repository.fetch(id);
		LOG.debug("<<< fetch(), id: " + fetchedModel.id());
		return fetchedModel;
	}


	protected List<T> handleFetchAll(IRepository<T> repository) {
		LOG.debug(">>> fetchAll()... ");
		List<T> fetchedModels = repository.fetchAll();
		LOG.debug("<<< fetchAll(), #fetchedModels: " + fetchedModels.size());
		return fetchedModels;
	}


	protected T handleRemove(String id, IRepository<T> repository) {
		LOG.debug(">>> remove()... ");
		T removedModel = repository.remove(id);
		LOG.debug("<<< remove(), id: " + (removedModel != null ? removedModel.id() : "N/A"));
		return removedModel;
	}


	protected boolean handleRemoveAll(IRepository<T> repository) {
		LOG.debug(">>> removeAll()... ");
		boolean removedAllOk = repository.removeAll();
		LOG.debug("<<< removeAll(), removedAllOk: " + removedAllOk);
		return removedAllOk;
	}

}
