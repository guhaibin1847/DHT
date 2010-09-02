package com.github.bcap.dht.server.handler;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Node;

public abstract class RequestHandler <Req extends Request, Resp extends Response> {
	
	public Resp handle(Node node, Req request) throws RequestHandlerException {
		return this.handleImpl(node, request);
	}
	
	public abstract Resp handleImpl(Node node, Req request) throws RequestHandlerException;
}
