package se.fkarbing.spring.mvc.common.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import se.fkarbing.core.model.GenericModel;

public class Company extends GenericModel implements Comparable<Company> {

	@NotNull
	@JsonProperty()
	private String name;
	@NotNull
	@JsonProperty()
	private String address;
	@NotNull
	@JsonProperty()
	private String latlng;


	/*
	 * @NotNull
	 * 
	 * @JsonProperty() private double[] latLng;
	 */

	public Company(String id, String name, String address, String latlng) {
		super(id);
		this.name = name;
		this.address = address;
		this.latlng = latlng;
	}


	/** Jackson */
	protected Company() {
	};


	public void changeName(String newName) {
		this.name = newName;
	}


	public String name() {
		return this.name;
	}


	@Override
	public int compareTo(Company o) {
		return this.name.compareTo(o.name);
	}

}
