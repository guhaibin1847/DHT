package com.github.bcap.dht.client.operation;

import com.github.bcap.dht.client.MessageSender;
import com.github.bcap.dht.client.ResponseHandler;
import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;

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