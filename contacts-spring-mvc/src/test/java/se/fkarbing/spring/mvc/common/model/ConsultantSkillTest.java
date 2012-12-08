package se.fkarbing.spring.mvc.common.model;

import org.junit.Assert;
import org.junit.Test;

import se.fkarbing.spring.mvc.common.model.mock.ConsultantSkillMock;
import se.fkarbing.utils.json.JackSONUtils;

public class ConsultantSkillTest {

	@Test
	public void testToFromJson() {
		ConsultantSkill myModel1 = ConsultantSkillMock.newIntance();
		String toJson1 = JackSONUtils.toJSON(myModel1);
		System.out.println(toJson1);
		ConsultantSkill fromJSON1 = JackSONUtils.fromJSON(toJson1, ConsultantSkill.class);
		Assert.assertEquals(myModel1, fromJSON1);
		// Use Constructor - assure id is the same..
		ConsultantSkill myModel12 = new ConsultantSkill(myModel1.id(), myModel1.name());
		Assert.assertEquals(myModel1, myModel12);

		// Do it all again
		ConsultantSkill myModel2 = ConsultantSkillMock.newIntance();
		String toJson2 = JackSONUtils.toJSON(myModel2);
		System.out.println(toJson2);
		ConsultantSkill fromJSON2 = JackSONUtils.fromJSON(toJson2, ConsultantSkill.class);
		Assert.assertEquals(myModel2, fromJSON2);

		// Assert ConsultantSkill 1 != 2
		Assert.assertNotSame(myModel1, myModel2);

	}

}
