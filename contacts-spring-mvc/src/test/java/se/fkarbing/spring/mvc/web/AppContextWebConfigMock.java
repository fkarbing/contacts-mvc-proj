package se.fkarbing.spring.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;

import se.fkarbing.spring.mvc.common.AppContextCommonConfigRIAK;
import se.fkarbing.spring.mvc.web.controller.CompanyController;
import se.fkarbing.spring.mvc.web.controller.ConsultantController;
import se.fkarbing.spring.mvc.web.controller.ConsultantSkillController;
import se.fkarbing.spring.mvc.web.controller.LocationController;
import se.fkarbing.spring.mvc.web.controller.MyModelController;

//@Configuration
public class AppContextWebConfigMock extends AppContextCommonConfigRIAK {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());


	/*------------------ Controller ------------------*/

	/** CompanyController */
	@Bean
	public CompanyController companyController() {
		CompanyController companyController = new CompanyController();
		LOG.trace("### Config companyController...");
		return companyController;
	}


	/** LocationController */
	@Bean
	public LocationController locationController() {
		LocationController locationController = new LocationController();
		LOG.trace("### Config locationController...");
		return locationController;
	}


	/** ConsultantSkillController */
	@Bean
	public ConsultantSkillController consultantSkillController() {
		ConsultantSkillController consultantSkillController = new ConsultantSkillController();
		LOG.trace("### Config consultantSkillController...");
		return consultantSkillController;
	}


	/** ConsultantController */
	@Bean
	public ConsultantController consultantController() {
		ConsultantController consultantController = new ConsultantController();
		LOG.trace("### Config consultantController...");
		return consultantController;
	}


	/** MyModelController */
	@Bean
	public MyModelController myModelController() {
		MyModelController myModelController = new MyModelController();
		LOG.trace("### Config myModelController...");
		return myModelController;
	}

	/*------------------ Interceptor ------------------*/

	/*
	 * @Bean public RESTInterceptor restInterceptor() { RESTInterceptor
	 * restInterceptor = new RESTInterceptor();
	 * LOG.info("### Config restInterceptor..."); return restInterceptor; }
	 */

}
