package com.github.bcap.dht.client;

import com.github.bcap.dht.message.request.Request;

public interface MessageSender {
	
	public void send(Request request, ResponseHandler handler);
	
}
