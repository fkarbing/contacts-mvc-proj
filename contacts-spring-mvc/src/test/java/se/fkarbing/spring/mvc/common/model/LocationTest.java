package se.fkarbing.spring.mvc.common.model;

import org.junit.Assert;
import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.mock.LocationMock;
import se.fkarbing.utils.json.JackSONUtils;

public class LocationTest {

	@Test
	public void testToFromJson() {
		Location myModel1 = LocationMock.newIntance();
		String toJson1 = JackSONUtils.toJSON(myModel1);
		System.out.println(toJson1);
		Location fromJSON1 = JackSONUtils.fromJSON(toJson1, Location.class);
		Assert.assertEquals(myModel1, fromJSON1);
		// Use Constructor - assure id is the same..
		Location myModel12 = new Location(myModel1.id(), myModel1.name());
		Assert.assertEquals(myModel1, myModel12);

		// Do it all again
		Location myModel2 = LocationMock.newIntance();
		String toJson2 = JackSONUtils.toJSON(myModel2);
		System.out.println(toJson2);
		Location fromJSON2 = JackSONUtils.fromJSON(toJson2, Location.class);
		Assert.assertEquals(myModel2, fromJSON2);

		// Assert Location 1 != 2
		Assert.assertNotSame(myModel1, myModel2);

	}

}
