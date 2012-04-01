package me.bcap.dht.message.request;

import java.io.Serializable;
import java.util.SortedMap;

import me.bcap.dht.message.Message;

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
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
		propertiesMap.put("timeout", timeout);
	}
}
