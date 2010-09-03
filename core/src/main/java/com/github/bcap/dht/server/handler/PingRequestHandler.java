package com.github.bcap.dht.server.handler;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.PingResponse;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Node;

public class PingRequestHandler extends RequestHandler {

	public Response handleImpl(Node node, Request request) {
		return new PingResponse();
	}

}
