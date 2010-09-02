package com.github.bcap.dht.message.request;

import java.io.Serializable;

import com.github.bcap.dht.message.Message;

public abstract class Request extends Message implements Serializable {

	protected static final long serialVersionUID = Message.serialVersionUID + 1L;

	public static final long DEFAULT_TIMEOUT = 10000;
	
	private transient long timeout;

	public Request() {
		super();
		setConversationId(getNextConversationId());
		this.timeout = DEFAULT_TIMEOUT;
	}
	
	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}
}
