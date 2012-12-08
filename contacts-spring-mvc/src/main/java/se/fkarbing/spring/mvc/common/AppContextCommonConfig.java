package se.fkarbing.spring.mvc.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.fkarbing.spring.mvc.common.model.Company;
import se.fkarbing.spring.mvc.common.model.Consultant;
import se.fkarbing.spring.mvc.common.model.ConsultantSkill;
import se.fkarbing.spring.mvc.common.model.Location;
import se.fkarbing.spring.mvc.common.model.MyModel;
import se.fkarbing.spring.mvc.core.repository.GenericRepositoryInMemory;
import se.fkarbing.spring.mvc.core.repository.InMemoryModelIdManager;
import se.fkarbing.spring.mvc.core.repository.ModelIdManager;

@Configuration
public class AppContextCommonConfig {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());


	/*------------------ Repository - InMemory ------------------*/

	/* GenericRepositoryInMemory<Company> */

	@Bean
	public GenericRepositoryInMemory<Company> companyRepository() {
		GenericRepositoryInMemory<Company> companyRepository = new GenericRepositoryInMemory<Company>();
		LOG.trace("### AppContextCommonConfig: companyRepository...");
		return companyRepository;
	}


	/* GenericRepositoryInMemory<Location> */

	@Bean
	public GenericRepositoryInMemory<Location> locationRepository() {
		GenericRepositoryInMemory<Location> locationRepository = new GenericRepositoryInMemory<Location>();
		LOG.trace("### AppContextCommonConfig: locationRepository...");
		return locationRepository;
	}


	/* GenericRepositoryInMemory<ConsultantSkill> */

	@Bean
	public GenericRepositoryInMemory<ConsultantSkill> consultantSkillRepository() {
		GenericRepositoryInMemory<ConsultantSkill> consultantSkillRepository = new GenericRepositoryInMemory<ConsultantSkill>();
		LOG.trace("### AppContextCommonConfig: consultantSkillRepository...");
		return consultantSkillRepository;
	}


	/* GenericRepositoryInMemory<Consultant> */

	@Bean
	public GenericRepositoryInMemory<Consultant> consultantRepository() {
		GenericRepositoryInMemory<Consultant> consultantRepository = new GenericRepositoryInMemory<Consultant>();
		LOG.trace("### AppContextCommonConfig: consultantRepository...");
		return consultantRepository;
	}


	/* GenericRepositoryInMemory<MyModel> */

	@Bean
	public GenericRepositoryInMemory<MyModel> modelRepository() {
		GenericRepositoryInMemory<MyModel> modelRepository = new GenericRepositoryInMemory<MyModel>();
		LOG.trace("### AppContextCommonConfig: modelRepository...");
		return modelRepository;
	}


	/*------------------ RepositoryIdManager ------------------*/

	@Bean
	public ModelIdManager idManager() {
		ModelIdManager idManager = new InMemoryModelIdManager();
		LOG.info("### AppContextCommonConfig: idManager...");
		return idManager;
	}

	/*------------------ CacheLoader ------------------*/

	/*
	 * @Bean public MyModelCacheLoader myModelCacheLoader() { MyModelCacheLoader
	 * myModelCacheLoader = new MyModelCacheLoader(); return myModelCacheLoader;
	 * }
	 */

}
