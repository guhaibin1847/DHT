package com.github.bcap.dht.client.operation;

import com.github.bcap.dht.client.MessageSender;
import com.github.bcap.dht.client.ResponseHandler;
import com.github.bcap.dht.message.request.Request;

public abstract class Operation implements ResponseHandler {

	protected MessageSender messageServer;
	
	protected void sendRequest(Request request) {
		messageServer.send(request, this);
	}
	
	public MessageSender getMessageServer() {
		return messageServer;
	}

	public void setMessageServer(MessageSender server) {
		this.messageServer = server;
	}
}
