package se.fkarbing.core.repository;

import java.util.List;

import javax.validation.constraints.NotNull;

import se.fkarbing.core.model.GenericModel;

public interface IRepository<T extends GenericModel> {

	public T fetch(@NotNull String id);


	public List<T> fetchAll();


	public T store(T model);


	public T remove(String id);


	public boolean removeAll();

}