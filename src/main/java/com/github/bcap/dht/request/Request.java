package com.github.bcap.dht.request;

public abstract class Request {
	
	private static long REQUEST_COUNTER = 0;
	
	private long requestId = REQUEST_COUNTER++;
	
	public long getRequestId() {
		return this.requestId;
	}

}
