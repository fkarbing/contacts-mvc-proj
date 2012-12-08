package se.fkarbing.utils.json;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser.Feature;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import se.fkarbing.spring.mvc.common.model.MyModel;

/**
 * @author fredrik.karbing
 * 
 *         Provides easy access to common jackson functionality,
 */
public class JackSONUtils {

	private static final Logger LOG = LoggerFactory.getLogger(JackSONUtils.class);
	private static final ObjectMapper mapper = new ObjectMapper(); // thread
	                                                               // safe.

	static {
		mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
	}


	/**
	 * Converts the object passed as argument to a JSON string.
	 * 
	 * @param obj
	 *            - The object to serialize.
	 * @return - The JSON representation of the object passed as argument OR
	 *         null if an error occured.
	 */
	public static String toJSON(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			LOG.error(e.toString(), e);
			return null;
		}
	}


	/**
	 * Converts the JSON string to the class passed as argument.
	 * 
	 * @param <T>
	 *            - The type (Class) to convert to.
	 * @param jsonStr
	 *            - The JSON data.
	 * @param clazz
	 *            - The class instance.
	 * @return - A instantiated object of type T, OR null if an error occured.
	 */
	public static <T> T fromJSON(String jsonStr, Class<T> clazz) {
		try {
			return mapper.readValue(jsonStr, clazz);
		} catch (IOException e) {
			LOG.error(e.toString(), e);
			return null;
		}
	}


	public static <T> T fromJSON(String jsonStr, TypeReference<T> typeReference) {
		T myObjects = null;
		try {
			myObjects = mapper.readValue(getJsonReqStore(), typeReference);
		} catch (IOException e) {
			LOG.error(e.toString(), e);
			return null;
		}
		return myObjects;
	}


	/**
	 * Converts a escaped json ('s are used instead of "s) to valid json.
	 * 
	 * @param escapedJson
	 * @return
	 */
	public static String jsonify(String escapedJson) {
		return escapedJson.replace("'", "\"");
	}


	// ---------- LOCALE TEST (main) -----------//

	public static void main(String[] args) throws JsonParseException, JsonMappingException,
	        IOException {

		// -------- LISTS ---------//

		// TypeReference: Raw conversion
		List<Map<String, String>> myObjects = mapper.readValue(getJsonReqStore(),
		        new TypeReference<List<Map<String, String>>>() {
		        });
		System.out.println(myObjects.toString());

		// TypeReference: fromJSON
		TypeReference<List<HashMap<String, String>>> typeReference = new TypeReference<List<HashMap<String, String>>>() {
		};
		List<HashMap<String, String>> fromJSON = JackSONUtils.fromJSON(getJsonReqStore(),
		        typeReference);
		// fromJson
		System.out.println(fromJSON.toString());
		// toJson
		System.out.println(JackSONUtils.toJSON(fromJSON));

		// MyModel
		MyModel user = new MyModel(null, "kalle anka");
		String json2 = JackSONUtils.toJSON(user);
		System.out.println(json2);
		System.out.println(JackSONUtils.fromJSON(json2, MyModel.class));
	}


	/**
	 * Creates a String representing an json array with with 2 plain json
	 * objects.
	 */
	private static String getJsonReqStore() {
		// JSONRequest: {"op":"store", "ids": null, "dtos":
		// "[{'id':'','alias':'value1444','email':'value2444','firstName':'value3444','lastNames':'value4444'}]",
		// "id": 1497363882}
		String strJsonReq = "[{'id':'','alias':'value1444','email':'value2444','firstName':'value3444','lastNames':'value4444'}, {'id':'','alias':'value1444','email':'value2444','firstName':'value3444','lastNames':'value4444'}]";
		return strJsonReq;
	}

}
