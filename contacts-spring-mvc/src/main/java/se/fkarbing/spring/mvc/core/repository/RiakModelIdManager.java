package se.fkarbing.spring.mvc.core.repository;

import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import se.fkarbing.core.model.GenericModel;
import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.utils.imutable.ImutableUtil;

/**
 * Uses UUID.randomUUID() which is syncronized.
 * 
 * Could be a bottleneck if large number of items are created
 * "at the same time".
 * 
 * @author fredrik.karbing
 * 
 * @param <T>
 */
@Component
public class RiakModelIdManager implements ModelIdManager {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());


	@Override
	public void setNewId(GenericModel model) {
		if (model.id() != null)
			throw new IllegalStateException("Error: setNewId() on model with existing id!.");
		this.injectId(model);
		LOG.debug("Model ID injected: " + model.toString());
	}


	@Override
	public void validateId(@Valid @NotNull final String id) throws NullPointerException,
	        IllegalArgumentException {
		// TODO: gives 500, handle this...
		// UUID.fromString(id);
	}


	protected synchronized void injectId(GenericModel model) {
		System.out.println(">>> RiakModelIdManager...");
		try {
			ImutableUtil.setParentNonPublicField(model, GenericModel.ID_FIELD_NAME, nextId());
			ImutableUtil.setNonPublicField(model, GenericModel.KEY_FIELD_NAME, model.id());
		} catch (Exception e) {
			// should never happen...
			LOG.error("Error - injectId,  msg: " + e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}


	private String nextId() {
		return UUID.randomUUID().toString();
	}


	// TODO: move to test class...
	public static void main(String[] args) {
		RiakModelIdManager idManager = new RiakModelIdManager();
		// set new id
		MyModel model = new MyModel(null, "a model");
		idManager.setNewId(model);
		// validate
		MyModel model2 = new MyModel(null, null);
		try {
			idManager.validateId(model2.id());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model2 = new MyModel(UUID.randomUUID().toString(), null);
		try {
			idManager.validateId(model2.id());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
