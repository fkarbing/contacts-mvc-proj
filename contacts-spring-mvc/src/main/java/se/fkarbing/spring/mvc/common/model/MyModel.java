package se.fkarbing.spring.mvc.common.model;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;

import se.fkarbing.core.model.GenericModel;

import com.basho.riak.client.convert.RiakKey;

public class MyModel extends GenericModel implements Comparable<MyModel> {

	@NotNull
	@JsonProperty()
	private String name;

	@JsonIgnore()
	@RiakKey
	private String key;


	public MyModel(String id, String name) {
		super(id);
		this.key = id;
		this.name = name;
	}


	/** Jackson */
	protected MyModel() {
	};


	public void changeName(String newName) {
		this.name = newName;
	}


	public String name() {
		return this.name;
	}


	@Override
	public int compareTo(MyModel o) {
		return this.name.compareTo(o.name);
	}

	/*
	 * @Override public int compareTo(MyModel o) { return
	 * this.name.compareTo(o.name); }
	 */

}
