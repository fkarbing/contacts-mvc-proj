package se.fkarbing.spring.mvc.common.model.mock;

import se.fkarbing.spring.mvc.common.model.ConsultantSkill;

public final class ConsultantSkillMock {

	/** Generates a new instance, setting the name to: xxx: (1:1000) */
	public static ConsultantSkill newIntance() {
		return new ConsultantSkill(null, "mock model: " + ((int) (1000 * Math.random())));
	}


	/** Generates a new instance, setting the name to: xxx: id */
	public static ConsultantSkill existingIntance(String id) {
		return new ConsultantSkill(id, "mock model: " + id);
	}
}