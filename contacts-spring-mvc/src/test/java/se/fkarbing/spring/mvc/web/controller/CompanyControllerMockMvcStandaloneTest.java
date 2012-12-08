package se.fkarbing.spring.mvc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.DispatcherServlet;

import se.fkarbing.spring.mvc.common.AppContextCommonConfigRIAK;
import se.fkarbing.spring.mvc.common.model.Company;
import se.fkarbing.spring.mvc.common.model.mock.CompanyMock;
import se.fkarbing.spring.mvc.core.util.MvcControllerUtil;
import se.fkarbing.spring.mvc.web.AppContextWebConfig;
import se.fkarbing.spring.mvc.web.util.ResultActionsUtil;
import se.fkarbing.utils.json.JackSONUtils;

/*import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;*/

/**
 * Tests all the CompanyController<Company> methods using the {@link MockMvc}
 * object.
 * 
 * Test as a "Standalone" meaning that the obtained MockMvc has "access" to the
 * service methods of the specified controller(s) and can perform a REST request
 * simulating the {@link DispatcherServlet}
 * 
 * Dependencies are loaded through: {@link AppContextCommonConfigRIAK}
 * 
 * <ol>
 * <li>create - creates a {@link Customer }
 * <li>fetch - returns a {@link Customer }
 * <li>fetchAll - returns a {@link List<Customer> }
 * <li>update - updates a {@link Customer }
 * <li>remove - removes a {@link Customer }
 * <li>removeAll - removes all {@link Customer }
 * </ol>
 * 
 * @author fredrik.karbing
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppContextWebConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class CompanyControllerMockMvcStandaloneTest {

	private static Logger LOG = LoggerFactory
	        .getLogger(CompanyControllerMockMvcStandaloneTest.class);

	// Resource path
	private final String resourcePath = MvcControllerUtil.resourcePath(Company.class);

	@Autowired
	private CompanyController companyController;

	private MockMvc mockMvc;


	@Before
	public void before() {
		mockMvc = MockMvcBuilders.standaloneSetup(companyController).build();
	}


	// TODO: verify location!
	@Test
	public void testCreate() throws Exception {
		_postMockModel();
		// ResultActionsUtil.log(resultActions);
	}


	@Test
	public void testCreateInvalidModel() throws Exception {

		Company company = new Company(null, null, null, null);
		String json = JackSONUtils.toJSON(company);
		LOG.info("company json: " + json);

		// -------- Request --------//

		MockHttpServletRequestBuilder requestBuilder = post(resourcePath).content(json);
		ResultActions resultActions = mockMvc.perform(requestBuilder.accept(
		        MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		// -------- Response --------//

		// Check: Status == 400 (bad request)
		resultActions.andExpect(status().isBadRequest());
		ResultActionsUtil.log(resultActions);
	}


	@Test
	public void testUpdate() throws Exception {

		// Create item
		Company storedItem = _postMockModel();
		LOG.info("stored item: " + storedItem);

		// Update by id
		String resourcePathWithId = MvcControllerUtil.resourcePathWithId(storedItem);
		storedItem.changeName("a new name");
		String putJsonStr = JackSONUtils.toJSON(storedItem);
		LOG.info("company putJsonStr: " + putJsonStr + " resourcePathWithId: " + resourcePathWithId);
		ResultActions resultActions = mockMvc
		        .perform(
		                put(resourcePathWithId).content(putJsonStr)
		                        .accept(MediaType.APPLICATION_JSON)
		                        .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

		Company updatedItem = ResultActionsUtil.contentAsJson(resultActions, Company.class);
		Assert.assertEquals(storedItem, updatedItem);
		// debug the result
		ResultActionsUtil.log(resultActions);

	}


	// TODO: Get location from Response and use that id!
	@Test
	public void testFetch() throws Exception {

		// Create item
		Company storedItem = _postMockModel();
		LOG.info("stored item: " + storedItem);

		// Fetch by id
		String resourcePathWithId = MvcControllerUtil.resourcePathWithId(storedItem);
		LOG.info("fetch with resourcePathWithId: " + resourcePathWithId);
		ResultActions resultActions = mockMvc.perform(get(resourcePathWithId).accept(
		        MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(
		        content().contentType(MediaType.APPLICATION_JSON));

		Company fetchedItem = ResultActionsUtil.contentAsJson(resultActions, Company.class);
		Assert.assertEquals(storedItem, fetchedItem);

		// debug the result
		// ResultActionsUtil.log(resultActions);
	}


	@Test
	public void testFetchAll() throws Exception {

		// Create 3
		for (int i = 0; i < 3; i++) {
			String json = JackSONUtils.toJSON(CompanyMock.newIntance());
			LOG.info("company json: " + json);
			mockMvc.perform(post(resourcePath).content(json).accept(MediaType.APPLICATION_JSON)
			        .contentType(MediaType.APPLICATION_JSON));
		}
		// Fetch
		MockHttpServletRequestBuilder requestBuilder = get(resourcePath);
		ResultActions resultActions = mockMvc.perform(requestBuilder
		        .accept(MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(
		        content().contentType(MediaType.APPLICATION_JSON));
		// debug the result
		// ResultActionsUtil.log(resultActions);
	}


	@Test
	public void testRemove() throws Exception {

		// Create item
		Company storedItem = _postMockModel();
		LOG.info("stored item: " + storedItem);

		// Remove by id
		String resourcePathWithId = MvcControllerUtil.resourcePathWithId(storedItem);
		LOG.info("fetch with resourcePathWithId: " + resourcePathWithId);
		ResultActions resultActions = mockMvc.perform(delete(resourcePathWithId).accept(
		        MediaType.APPLICATION_JSON));
		resultActions.andExpect(status().isOk()).andExpect(
		        content().contentType(MediaType.APPLICATION_JSON));

		Company removedItem = ResultActionsUtil.contentAsJson(resultActions, Company.class);
		Assert.assertEquals(storedItem, removedItem);

		// debug the result
		// ResultActionsUtil.log(resultActions);
	}


	@Test
	public void testRemoveAll() throws Exception {

		// Create 3
		for (int i = 0; i < 3; i++) {
			String json = JackSONUtils.toJSON(CompanyMock.newIntance());
			LOG.info("company json: " + json);
			mockMvc.perform(
			        delete(resourcePath).content(json).accept(MediaType.APPLICATION_JSON)
			                .contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
			        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
		}
		// RemoveAll
		MockHttpServletRequestBuilder requestBuilder = delete(resourcePath).accept(
		        MediaType.APPLICATION_JSON);
		ResultActions resultActions = mockMvc.perform(requestBuilder);
		resultActions.andExpect(status().isOk()).andExpect(
		        content().contentType(MediaType.APPLICATION_JSON));
		// debug the result
		// ResultActionsUtil.log(resultActions);
	}


	/**
	 * Helper method to create (persist) a model.
	 * 
	 * @return
	 * @throws Exception
	 */
	private Company _postMockModel() throws Exception {
		String jsonStr = JackSONUtils.toJSON(CompanyMock.newIntance());
		LOG.info("company json: " + jsonStr);
		// Post request
		ResultActions resultActions = mockMvc.perform(post(resourcePath).content(jsonStr)
		        .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON));

		// Validate response
		resultActions.andExpect(status().isCreated()).andExpect(
		        content().contentType(MediaType.APPLICATION_JSON));

		Company storedItem = ResultActionsUtil.contentAsJson(resultActions, Company.class);
		LOG.info("storedItem: " + storedItem);
		return storedItem;
	}

}
