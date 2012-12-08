package se.fkarbing.spring.mvc.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import se.fkarbing.spring.mvc.common.AppContextCommonConfigRIAK;
import se.fkarbing.spring.mvc.web.controller.MyModelController;

@Configuration
public class MyModelContextWebConfig extends AppContextCommonConfigRIAK {

	protected Logger LOG = LoggerFactory.getLogger(this.getClass());


	/*------------------ Controller ------------------*/

	/** MyModelController */
	@Bean
	public MyModelController myModelController() {
		MyModelController myModelController = new MyModelController();
		LOG.trace("### Config myModelController...");
		return myModelController;
	}

}
