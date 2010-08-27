package com.github.bcap.dht.operation;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.server.MessageServer;
import com.github.bcap.dht.server.ResponseHandler;

public abstract class Operation <Req extends Request, Resp extends Response> implements ResponseHandler<Resp> {

	protected MessageServer messageServer;
	
	public void sendRequest(Req request) {
		messageServer.send(request, this);
	}
	
	public MessageServer getMessageServer() {
		return messageServer;
	}

	public void setMessageServer(MessageServer server) {
		this.messageServer = server;
	}
}
