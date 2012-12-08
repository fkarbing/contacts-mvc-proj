package se.fkarbing.spring.mvc.core.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import se.fkarbing.core.model.GenericModel;

public interface IMvcController<T extends GenericModel> {

	public abstract ResponseEntity<T> create(@RequestBody @Valid final T model,
			HttpServletRequest request);

	public abstract ResponseEntity<T> update(@RequestBody @Valid final T model,
			@PathVariable final String id);

	public abstract T fetch(@PathVariable final String id);

	public abstract List<T> fetchAll();

	public T remove(@PathVariable final String id);

	public boolean removeAll();
}