package se.fkarbing.spring.mvc.core.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import se.fkarbing.core.model.GenericModel;

public class MvcControllerUtil {

	public static <T extends GenericModel> ResponseEntity<T> newResponseCreated(T model,
	        HttpServletRequest request) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add(com.google.common.net.HttpHeaders.LOCATION,
		        resourcePathWithId(request.getRequestURL().toString(), model));
		return new ResponseEntity<T>(model, responseHeaders, HttpStatus.CREATED);
	}


	public static String resourcePathWithId(GenericModel model) {
		return resourcePathWithId(resourcePath(model.getClass()), model);
	}


	/**
	 * 
	 * @param requestURL
	 *            - should be of absolute path format, i.e. http://xxx or /xxx
	 * @param model
	 * @return
	 */
	public static String resourcePathWithId(String requestURL, GenericModel model) {
		return String.format("%s/%s", requestURL, model.id());
	}


	/**
	 * Returns the resource path for the model passed as argument. Note:
	 * Returned in its plural form, i.e. MyModel --> mymodels
	 * 
	 * @param clazz
	 * @return
	 */
	public static String resourcePath(Class<? extends GenericModel> clazz) {
		return String.format("/%ss", clazz.getSimpleName().toLowerCase());
	}

}
