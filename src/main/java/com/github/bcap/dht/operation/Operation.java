package com.github.bcap.dht.operation;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.server.MessageSender;
import com.github.bcap.dht.server.ResponseHandler;

public abstract class Operation <Req extends Request, Resp extends Response> implements ResponseHandler<Resp> {

	protected MessageSender messageServer;
	
	public void sendRequest(Req request) {
		messageServer.send(request, this);
	}
	
	public MessageSender getMessageServer() {
		return messageServer;
	}

	public void setMessageServer(MessageSender server) {
		this.messageServer = server;
	}
}
