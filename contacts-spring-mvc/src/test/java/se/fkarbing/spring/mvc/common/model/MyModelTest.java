package se.fkarbing.spring.mvc.common.model;

import org.junit.Assert;
import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.mock.MyModelMock;
import se.fkarbing.utils.json.JackSONUtils;

public class MyModelTest {

	@Test
	public void testToFromJson() {
		MyModel myModel1 = MyModelMock.newIntance();
		String toJson1 = JackSONUtils.toJSON(myModel1);
		System.out.println(toJson1);
		MyModel fromJSON1 = JackSONUtils.fromJSON(toJson1, MyModel.class);
		Assert.assertEquals(myModel1, fromJSON1);
		// Use Constructor - assure id is the same..
		MyModel myModel12 = new MyModel(myModel1.id(), myModel1.name());
		Assert.assertEquals(myModel1, myModel12);

		// Do it all again
		MyModel myModel2 = MyModelMock.newIntance();
		String toJson2 = JackSONUtils.toJSON(myModel2);
		System.out.println(toJson2);
		MyModel fromJSON2 = JackSONUtils.fromJSON(toJson2, MyModel.class);
		Assert.assertEquals(myModel2, fromJSON2);

		// Assert MyModel 1 != 2
		Assert.assertNotSame(myModel1, myModel2);

		String jsonStr = "{\"id\":\"3242342\",\"name\":\"mock model: 526\"}";
		MyModel fromJSONX = JackSONUtils.fromJSON(jsonStr, MyModel.class);

		System.out.println("fromJSONX: " + fromJSONX);

	}


	@Test
	public void testToFromJsonAsRequest() {

		String toJson1 = "{\"id\":null,\"name\":\"mock model: 526\"}";
		System.out.println("toJson1: " + toJson1);
		MyModel myModel1 = JackSONUtils.fromJSON(toJson1, MyModel.class);
		System.out.println("myModel1: " + myModel1);

		String toJson2 = JackSONUtils.toJSON(myModel1);
		System.out.println(toJson2);

		String existingJsonStr = "{\"id\":\"3242342\",\"name\":\"mock model: 526\"}";

	}

}
