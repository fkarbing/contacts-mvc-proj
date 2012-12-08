package se.fkarbing.spring.mvc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import se.fkarbing.core.repository.IRepository;
import se.fkarbing.riak.client.RiakRepository;
import se.fkarbing.spring.mvc.common.model.Company;
import se.fkarbing.spring.mvc.common.model.Consultant;
import se.fkarbing.spring.mvc.common.model.ConsultantSkill;
import se.fkarbing.spring.mvc.common.model.Location;
import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.core.repository.ModelIdManager;
import se.fkarbing.spring.mvc.core.repository.RiakModelIdManager;

public class AppContextCommonConfigRIAK {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());


	/*------------------ Repository - RiakClient ------------------*/

	/**
	 * RiakRepository
	 * 
	 * @throws Exception
	 */
	/*
	 * @Bean public IRiakClient riakClient() throws Exception { IRiakClient
	 * riakPbcClient = RiakFactory.pbcClient("127.0.0.1", 8089);
	 * LOG.info("### AppContextCommonConfig: riakPbcClient..."); return
	 * riakPbcClient; }
	 */

	/*------------------ Repository - RiakRepository ------------------*/

	/** RiakRepository<Company> */
	@Bean
	public IRepository<Company> companyRepository() {
		RiakRepository<Company> companyRepository = new RiakRepository<Company>(Company.class);
		LOG.debug("### AppContextCommonConfig: companyRepository...");
		return companyRepository;
	}


	/** RiakRepository<Location> */
	@Bean
	public IRepository<Location> locationRepository() {
		RiakRepository<Location> locationRepository = new RiakRepository<Location>(Location.class);
		LOG.debug("### AppContextCommonConfig: locationRepository...");
		return locationRepository;
	}


	/** RiakRepository<ConsultantSkill> */
	@Bean
	public IRepository<ConsultantSkill> consultantSkillRepository() {
		RiakRepository<ConsultantSkill> consultantSkillRepository = new RiakRepository<ConsultantSkill>(
		        ConsultantSkill.class);
		LOG.debug("### AppContextCommonConfig: consultantSkillRepository...");
		return consultantSkillRepository;
	}


	/** RiakRepository<Consultant> */
	@Bean
	public IRepository<Consultant> consultantRepository() {
		RiakRepository<Consultant> consultantRepository = new RiakRepository<Consultant>(
		        Consultant.class);
		LOG.debug("### AppContextCommonConfig: consultantRepository...");
		return consultantRepository;
	}


	/** RiakRepository<MyModel> */
	@Bean
	public IRepository<MyModel> myModelRepository() {
		RiakRepository<MyModel> myModelRepository = new RiakRepository<MyModel>(MyModel.class);
		LOG.debug("### AppContextCommonConfig: myModelRepository...");
		return myModelRepository;
	}


	/*------------------ RepositoryIdManager ------------------*/

	/** */
	/*
	 * @Bean public ModelIdManager idManager() { ModelIdManager idManager = new
	 * InMemoryModelIdManager();
	 * LOG.info("### AppContextCommonConfig: idManager..."); return idManager; }
	 */

	/** */
	@Bean
	public ModelIdManager riakIdManager() {
		ModelIdManager riakIdManager = new RiakModelIdManager();
		LOG.info("### AppContextCommonConfig: riakIdManager...");
		return riakIdManager;
	}

	/*------------------ CacheLoader ------------------*/

	/*
	 * @Bean public MyModelCacheLoader myModelCacheLoader() { MyModelCacheLoader
	 * myModelCacheLoader = new MyModelCacheLoader(); return myModelCacheLoader;
	 * }
	 */

}
