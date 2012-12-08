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
import se.fkarbing.spring.mvc.common.model.Consultant;
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
@RequestMapping("/consultants")
public class ConsultantController extends XSimpleMvcController<Consultant> implements
        IMvcController<Consultant> {

	@Autowired
	@Qualifier("consultantRepository")
	GenericRepositoryInMemory<Consultant> consultantRepository;


	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Consultant> create(@RequestBody @Valid final Consultant model,
	        final HttpServletRequest request) {
		return super.handleCreate(model, consultantRepository, request);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Consultant> update(@RequestBody @Valid final Consultant model,
	        @PathVariable final String id) {
		return super.handleUpdate(model, consultantRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Consultant fetch(@PathVariable final String id) {
		return super.handleFetch(id, consultantRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Consultant> fetchAll() {
		// TODO: DELETE when loaded properly
		mockFixData();
		return super.handleFetchAll(consultantRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Consultant remove(@PathVariable final String id) {
		return super.handleRemove(id, consultantRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean removeAll() {
		return super.handleRemoveAll(consultantRepository);
	}


	private void mockStartData() {
		List<Consultant> handleFetchAll = super.handleFetchAll(consultantRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockStartData - ConsultantController()...");
			String consultants[] = { "Fred Fredsson", "Lars Larsson", "Per Persson" };
			String emails[] = { "fred.fredsson", "lars.larsson", "per.persson" };
			String companys[] = { "H&M", "Aftonbladet", "Cygni" };
			String locations[] = { "Sodertälje", "Norrtälje", "Solna", "Hammarby" };
			String consultantskill[] = { "Java developer", "Scrum master", "DB Admin", "DevOps",
			        "Project manager", "Tester", "CTO" };

			for (int i = 0; i < consultants.length; i++) {
				Consultant l = new Consultant(null, consultants[i], emails[i] + "@cygni.se",
				        companys[i], locations[i], consultantskill[i]);
				this.consultantRepository.store(l);
			}
			LOG.warn("####### mockStartData - Created #consultants: "
			        + this.consultantRepository.fetchAll().size());
		}
	}


	private void mockFixData() {
		List<Consultant> handleFetchAll = super.handleFetchAll(consultantRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockFixData - ConsultantController()...");
			Consultant l = new Consultant(null, "Fred Fredsson", "fred.fredsson@cygni.se",
			        MockDataUitl.MOCK_COMPANY_ID, MockDataUitl.MOCK_LOCATION_ID,
			        MockDataUitl.MOCK_SKILL_ID);
			this.consultantRepository.store(l);
			LOG.warn("####### mockFixData - Created #consultants: "
			        + this.consultantRepository.fetchAll().size());
		}
	}

}
