package com.github.bcap.dht.server;

import com.github.bcap.dht.message.request.Request;

public interface MessageServer {
	
	public void send(Request request, ResponseHandler handler);
	
}
