package com.github.bcap.dht.message.response;

import java.io.Serializable;

import com.github.bcap.dht.message.Message;
import com.github.bcap.dht.message.request.Request;

public abstract class Response extends Message implements Serializable{
	
	protected static final long serialVersionUID = Message.serialVersionUID + 1L;

	public enum Status {
		OK,
		TIMEOUT
	}
	
	public Response(Request request) {
		super();
		setConversationId(request.getConversationId());
		status = Status.OK;
	}
	
	private Status status;
	
	public boolean isSuccess() {
		return this.status == Status.OK;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}
}
