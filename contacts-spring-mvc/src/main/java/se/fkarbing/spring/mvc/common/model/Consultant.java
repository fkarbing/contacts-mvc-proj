package se.fkarbing.spring.mvc.common.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import se.fkarbing.core.model.GenericModel;

public class Consultant extends GenericModel implements Comparable<Consultant> {

	@NotNull
	@JsonProperty()
	private String name;
	@NotNull
	@JsonProperty()
	private String email;
	@NotNull
	@JsonProperty()
	private String companyId;
	@NotNull
	@JsonProperty()
	private String locationId;
	@NotNull
	@JsonProperty()
	private String consultantskillId;


	public Consultant(String id, String name) {
		super(id);
		this.name = name;
	}


	public Consultant(String id, String name, String email, String companyId, String locationId,
	        String consultantskillId) {
		super(id);
		this.name = name;
		this.email = email;
		this.companyId = companyId;
		this.locationId = locationId;
		this.consultantskillId = consultantskillId;
	}


	/** Jackson */
	protected Consultant() {
	};


	public void changeName(String newName) {
		this.name = newName;
	}


	public String name() {
		return this.name;
	}


	public String companyId() {
		return this.companyId;
	}


	public String locationId() {
		return this.locationId;
	}


	public String consultantskillId() {
		return this.consultantskillId;
	}


	@Override
	public int compareTo(Consultant o) {
		return this.name.compareTo(o.name);
	}

}
