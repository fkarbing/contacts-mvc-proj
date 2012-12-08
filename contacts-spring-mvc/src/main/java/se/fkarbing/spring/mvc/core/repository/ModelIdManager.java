package se.fkarbing.spring.mvc.core.repository;

import javax.validation.constraints.NotNull;

import se.fkarbing.core.model.GenericModel;

public interface ModelIdManager {

	public void setNewId(GenericModel model);

	/**
	 * Validates the id.
	 * 
	 * @param id
	 *            - must NOT be: null OR unknown / invalid format.
	 * 
	 * @throws NullPointerException
	 *             - IF id is <code>null</code>.
	 * @throws IllegalArgumentException
	 *             - IF id is null OR of unknown / invalid format.
	 * 
	 */
	public void validateId(@NotNull final String id)
			throws NullPointerException, IllegalArgumentException;
}
