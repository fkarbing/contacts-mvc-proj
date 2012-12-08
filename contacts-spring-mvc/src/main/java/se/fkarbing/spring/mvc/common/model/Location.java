package se.fkarbing.spring.mvc.common.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonProperty;

import se.fkarbing.core.model.GenericModel;

public class Location extends GenericModel implements Comparable<Location> {

	@NotNull
	@JsonProperty()
	private String name;


	public Location(String id, String name) {
		super(id);
		this.name = name;
	}


	/** Jackson */
	protected Location() {
	};


	public void changeName(String newName) {
		this.name = newName;
	}


	public String name() {
		return this.name;
	}


	@Override
	public int compareTo(Location o) {
		return this.name.compareTo(o.name);
	}

}
