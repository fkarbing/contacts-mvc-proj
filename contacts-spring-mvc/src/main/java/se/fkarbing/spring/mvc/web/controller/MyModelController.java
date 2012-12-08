package se.fkarbing.spring.mvc.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.core.controller.IMvcController;
import se.fkarbing.spring.mvc.core.controller.XSimpleMvcController;
import se.fkarbing.spring.mvc.core.repository.GenericRepositoryInMemory;

/**
 * 
 * <h1>Supported methods:</h1>
 * <ol>
 * <li>create
 * <li>update
 * <li>fetch
 * <li>fetchAll
 * <li>remove
 * <li>removeAll
 * </ol>
 * 
 * @author fredrik.karbing
 * 
 */
@Controller
@RequestMapping("/mymodels")
public class MyModelController extends XSimpleMvcController<MyModel> implements
        IMvcController<MyModel> {

	@Autowired
	@Qualifier("modelRepository")
	GenericRepositoryInMemory<MyModel> modelRepository;


	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<MyModel> create(@RequestBody @Valid final MyModel model,
	        final HttpServletRequest request) {
		return super.handleCreate(model, modelRepository, request);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MyModel> update(@RequestBody @Valid final MyModel model,
	        @PathVariable final String id) {
		return super.handleUpdate(model, modelRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MyModel fetch(@PathVariable final String id) {
		return super.handleFetch(id, modelRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<MyModel> fetchAll() {
		return super.handleFetchAll(modelRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public MyModel remove(@PathVariable final String id) {
		return super.handleRemove(id, modelRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean removeAll() {
		return super.handleRemoveAll(modelRepository);
	}
}
