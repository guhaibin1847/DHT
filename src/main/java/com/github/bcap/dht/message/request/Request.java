package com.github.bcap.dht.message.request;

import com.github.bcap.dht.message.Message;

public abstract class Request extends Message {

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
