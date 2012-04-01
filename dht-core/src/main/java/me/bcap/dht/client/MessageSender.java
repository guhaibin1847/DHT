package me.bcap.dht.client;

import me.bcap.dht.message.request.Request;

public interface MessageSender {
	
	public void send(Request request, ResponseHandler handler);
	
}
