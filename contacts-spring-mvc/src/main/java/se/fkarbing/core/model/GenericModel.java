package se.fkarbing.core.model;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.codehaus.jackson.annotate.JsonProperty;

public abstract class GenericModel {

	public static final String ID_FIELD_NAME = "id";

	public static final String KEY_FIELD_NAME = "key";

	@JsonProperty
	protected String id;


	protected GenericModel(String id) {
		this.id = id;
	}


	/** Jackson */
	protected GenericModel() {
	};


	public String id() {
		return id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GenericModel other = (GenericModel) obj;
		if (this.id == null) {
			if (other.id != null)
				return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
