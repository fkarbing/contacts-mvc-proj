package se.fkarbing.spring.mvc.web.util;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.ResultActions;

import se.fkarbing.utils.json.JackSONUtils;

/**
 * Provides simplified access to {@link ResultActions} related functionality.
 * 
 * @author fredrik.karbing
 * 
 */
public class ResultActionsUtil {

	private static Logger LOG = LoggerFactory
			.getLogger(ResultActionsUtil.class);

	/**
	 * Logs the param: resultActions to stdout.
	 * 
	 * @param resultActions
	 */
	public static void log(ResultActions resultActions) {
		try {
			resultActions.andDo(print());
		} catch (Exception e) {
			e.printStackTrace(); // just a utility, just log
		}
	}

	/**
	 * @see {@link ResultActionsUtil#log(ResultActions)}
	 * 
	 *      Note: This method ONLY logs if log level is set to
	 *      <code>debug</code>
	 * 
	 * @param resultActions
	 */
	public static void debug(ResultActions resultActions) {
		if (LOG.isTraceEnabled() || LOG.isDebugEnabled()) {
			try {
				resultActions.andDo(print());
			} catch (Exception e) {
				e.printStackTrace(); // just a utility, just log
			}
		}
	}

	public static <T> T contentAsJson(ResultActions resultActions,
			Class<T> clazz) {
		try {
			String contentAsString = resultActions.andReturn().getResponse()
					.getContentAsString();
			return JackSONUtils.fromJSON(contentAsString, clazz);
		} catch (UnsupportedEncodingException e) {
			LOG.error(e.getMessage(), e);
			throw new IllegalStateException(e);
		}
	}
}
