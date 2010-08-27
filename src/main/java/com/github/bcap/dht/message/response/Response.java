package com.github.bcap.dht.message.response;

import com.github.bcap.dht.message.Message;

public abstract class Response extends Message {
	
	public enum Status {
		OK,
		TIMEOUT
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
