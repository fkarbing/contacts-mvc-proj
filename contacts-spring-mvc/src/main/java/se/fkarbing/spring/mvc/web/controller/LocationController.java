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

import se.fkarbing.spring.mock.MockDataUitl;
import se.fkarbing.spring.mvc.common.model.Location;
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
@RequestMapping("/locations")
public class LocationController extends XSimpleMvcController<Location> implements
        IMvcController<Location> {

	@Autowired
	@Qualifier("locationRepository")
	GenericRepositoryInMemory<Location> locationRepository;


	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Location> create(@RequestBody @Valid final Location model,
	        final HttpServletRequest request) {
		return super.handleCreate(model, locationRepository, request);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Location> update(@RequestBody @Valid final Location model,
	        @PathVariable final String id) {
		return super.handleUpdate(model, locationRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Location fetch(@PathVariable final String id) {
		return super.handleFetch(id, locationRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Location> fetchAll() {
		// TODO: DELETE when loaded properly
		// mockStartData();
		mockFixData();
		return super.handleFetchAll(locationRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Location remove(@PathVariable final String id) {
		return super.handleRemove(id, locationRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean removeAll() {
		return super.handleRemoveAll(locationRepository);
	}


	private void mockStartData() {
		List<Location> handleFetchAll = super.handleFetchAll(locationRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockStartData - LocationController()...");
			String locations[] = { "Sodertälje", "Norrtälje", "Solna", "Hammarby" };
			for (int i = 0; i < locations.length; i++) {
				Location l = new Location(null, locations[i]);
				this.locationRepository.store(l);
			}
			LOG.warn("####### mockStartData - Created #locations: "
			        + this.locationRepository.fetchAll().size());
		}
	}


	private void mockFixData() {
		List<Location> handleFetchAll = super.handleFetchAll(locationRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockFixData - LocationController()...");
			String locations[] = { "Sodertälje", "Norrtälje", "Solna", "Hammarby" };
			for (int i = 0; i < 1; i++) {
				Location l = new Location(MockDataUitl.MOCK_LOCATION_ID, locations[i]);
				this.locationRepository.store(l);
			}
			LOG.warn("####### mockFixData - Created #locations: "
			        + this.locationRepository.fetchAll().size());
		}
	}

}
