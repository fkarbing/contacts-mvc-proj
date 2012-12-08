package se.fkarbing.spring.mvc.common.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import se.fkarbing.core.model.GenericModel;

public class ConsultantSkill extends GenericModel implements Comparable<ConsultantSkill> {

	@NotNull
	@JsonProperty()
	private String name;


	public ConsultantSkill(String id, String name) {
		super(id);
		this.name = name;
	}


	/** Jackson */
	protected ConsultantSkill() {
	};


	public void changeName(String newName) {
		this.name = newName;
	}


	public String name() {
		return this.name;
	}


	@Override
	public int compareTo(ConsultantSkill o) {
		return this.name.compareTo(o.name);
	}

}
