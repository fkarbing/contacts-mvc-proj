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
import se.fkarbing.spring.mvc.common.model.Company;
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
@RequestMapping("/companys")
public class CompanyController extends XSimpleMvcController<Company> implements
        IMvcController<Company> {

	@Autowired
	@Qualifier("companyRepository")
	GenericRepositoryInMemory<Company> companyRepository;


	@Override
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Company> create(@RequestBody @Valid final Company model,
	        final HttpServletRequest request) {
		return super.handleCreate(model, companyRepository, request);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT,
	        consumes = MediaType.APPLICATION_JSON_VALUE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Company> update(@RequestBody @Valid final Company model,
	        @PathVariable final String id) {
		return super.handleUpdate(model, companyRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.GET,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Company fetch(@PathVariable final String id) {
		return super.handleFetch(id, companyRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Company> fetchAll() {
		// TODO: DELETE when loaded properly
		mockStartData();
		return super.handleFetchAll(companyRepository);
	}


	@Override
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
	        produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Company remove(@PathVariable final String id) {
		return super.handleRemove(id, companyRepository);
	}


	@Override
	@RequestMapping(method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public boolean removeAll() {
		return super.handleRemoveAll(companyRepository);
	}


	private void mockStartData() {
		List<Company> handleFetchAll = super.handleFetchAll(companyRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockStartData - CompanyController()...");
			String companys[] = { "H&M", "Aftonbladet", "Cygni" };
			String address[] = { "Liljeholmstorget 5", "Västra Järnvägsgatan 21 111 64 STOCKHOLM",
			        "Sturegatan 34, 114 36 Stockholm" };
			// double[] latLng = { 59.33230529999999, 18.05283570 };
			String[] latlng = { "59.3223,18.55283", "59.33230529999999,18.05283570",
			        "59.339762,18.075985" };
			for (int i = 0; i < companys.length; i++) {
				Company l = null;
				if (i == 0)
					l = new Company(MockDataUitl.MOCK_COMPANY_ID, companys[i], address[i],
					        latlng[i]);
				else
					l = new Company(null, companys[i], address[i], latlng[i]);

				this.companyRepository.store(l);
			}
			LOG.warn("####### mockStartData - Created #companys: "
			        + this.companyRepository.fetchAll().size());
		}
	}


	private void mockFixData() {
		List<Company> handleFetchAll = super.handleFetchAll(companyRepository);
		if (handleFetchAll.size() == 0) {
			LOG.warn("####### mockFixData - CompanyController()...");
			String companys[] = { "H&M", "Aftonbladet", "Cygni" };
			String address[] = { "Liljeholmstorget 5", "Västra Järnvägsgatan 21 111 64 STOCKHOLM",
			        "Sturegatan 34, 114 36 Stockholm" };
			// double[] latLng = { 59.33230529999999, 18.05283570 };
			String[] latlng = { "59.3223,18.55283", "59.33230529999999,18.05283570",
			        "59.339762,18.075985" };

			Company l = new Company(MockDataUitl.MOCK_COMPANY_ID, companys[0], address[0],
			        latlng[0]);
			this.companyRepository.store(l);
			LOG.warn("####### mockFixData - Created #consultants: "
			        + this.companyRepository.fetchAll().size());
		}
	}
}
