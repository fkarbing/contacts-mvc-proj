package se.fkarbing.utils.imutable;

import java.lang.reflect.Field;

/**
 * Sets non public fields through reflection.
 * 
 * Tests are done on the {@link ImImutable} object.
 * 
 * @author fredrik.karbing
 * 
 */
public class ImutableUtil {

	public static void setNonPublicField(final Object obj, final Field field, final Object newValue)
	        throws Exception {
		field.setAccessible(true);
		field.set(obj, newValue);
	}


	public static void setParentNonPublicField(final Object obj, final String fieldName,
	        final String value) throws Exception {
		Field field = obj.getClass().getSuperclass().getDeclaredField(fieldName);
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			if (field != null) {
				field.setAccessible(false);
			}
		}

	}


	public static void setNonPublicField(final Object obj, String fieldName, String value)
	        throws Exception {

		Field field = obj.getClass().getDeclaredField(fieldName);
		try {
			field.setAccessible(true);
			field.set(obj, value);
		} catch (Exception e) {
			if (field != null) {
				field.setAccessible(false);
			}
		}

	}

}
