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
import se.fkarbing.spring.mvc.common.model.ConsultantSkill;
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
@RequestMapping("/consultantskills")
public class ConsultantSkillController extends XSimpleMvcController<ConsultantSkill> implements
        IMvcController<ConsultantSkill> {

	@Autowired
	@Qualifier("consultantSkillRepository")
	GenericRepositoryInMemory<ConsultantSkill> consultantSkillRepository;


	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<ConsultantSkill> create(@RequestBody @Valid final ConsultantSkill model,
	        final HttpServletRequest request) {
		return super.handleCreate(model, consultantSkillRepository, request);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ConsultantSkill> update(@RequestBody @Valid final ConsultantSkill model,
	        @PathVariable final String id) {
		return super.handleUpdate(model, consultantSkillRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ConsultantSkill fetch(@PathVariable final String id) {
		return super.handleFetch(id, consultantSkillRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<ConsultantSkill> fetchAll() {
		// TODO: DELETE when loaded properly
		// mockStartData();
		mockFixData();
		return super.handleFetchAll(consultantSkillRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ConsultantSkill remove(@PathVariable final String id) {
		return super.handleRemove(id, consultantSkillRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean removeAll() {
		return super.handleRemoveAll(consultantSkillRepository);
	}


	private void mockStartData() {
		List<ConsultantSkill> handleFetchAll = super.handleFetchAll(consultantSkillRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockStartData - ConsultantSkillController()...");
			String consultantSkills[] = { "Java developer", "Scrum master", "DB Admin", "DevOps",
			        "Project manager", "Tester", "CTO" };
			for (int i = 0; i < consultantSkills.length; i++) {
				ConsultantSkill l = new ConsultantSkill(null, consultantSkills[i]);
				this.consultantSkillRepository.store(l);
			}
			LOG.warn("####### mockStartData - Created #consultantSkills: "
			        + this.consultantSkillRepository.fetchAll().size());
		}
	}


	private void mockFixData() {
		List<ConsultantSkill> handleFetchAll = super.handleFetchAll(consultantSkillRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockFixData - ConsultantSkillController()...");
			String consultantSkills[] = { "Java developer", "Scrum master", "DB Admin", "DevOps",
			        "Project manager", "Tester", "CTO" };
			for (int i = 0; i < 1; i++) {
				ConsultantSkill l = new ConsultantSkill(MockDataUitl.MOCK_SKILL_ID,
				        consultantSkills[i]);
				this.consultantSkillRepository.store(l);
			}
			LOG.warn("####### mockFixData - Created #consultantSkills: "
			        + this.consultantSkillRepository.fetchAll().size());
		}
	}
}
