package se.fkarbing.spring.mvc.web.util;

import org.springframework.mock.web.MockHttpServletRequest;

public class MvcMockRequestUtil {

	/**
	 * test helper: Only Accept header
	 * 
	 * @param method
	 * @param requestURI
	 * @param acceptHeader
	 * @return
	 */
	public static MockHttpServletRequest constructMockRequest(String method,
			String requestURI, String acceptHeader) {
		return constructMockRequest(method, requestURI, acceptHeader, null);

	}

	/**
	 * test helper: Accept & ContentType header
	 * 
	 * @param method
	 * @param requestURI
	 * @param acceptHeader
	 * @param contentTypeHeader
	 * @return
	 */
	public static MockHttpServletRequest constructMockRequest(String method,
			String requestURI, String acceptHeader, String contentTypeHeader) {

		MockHttpServletRequest mockRequest = new MockHttpServletRequest() {

			@Override
			public String getPathTranslated() {
				return null; // prevent Spring to resolve the file on the
								// filesystem which fails
			}

		};
		mockRequest.setMethod(method);
		mockRequest.setRequestURI(requestURI);
		if (acceptHeader != null)
			mockRequest.addHeader("Accept", acceptHeader);
		if (contentTypeHeader != null)
			mockRequest.addHeader("Content-Type", contentTypeHeader);
		return mockRequest;
	}

}
