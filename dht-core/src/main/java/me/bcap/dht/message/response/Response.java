package me.bcap.dht.message.response;

import java.io.Serializable;
import java.util.SortedMap;

import me.bcap.dht.message.Message;

public abstract class Response extends Message implements Serializable {

	protected static final long serialVersionUID = Message.serialVersionUID + 1L;

	public enum Status {
		OK, ERROR,
	}

	private Status status = Status.OK;

	public Response() {
		super();
	}

	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
	}
	
	public boolean isSuccess() {
		return this.status.equals(Status.OK);
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
