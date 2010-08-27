package com.github.bcap.dht.server;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.operation.Operation;

public interface MessageServer {
	
	public void send(Request request, Operation owner);
	
}
