package com.github.bcap.dht.server.handler;

import com.github.bcap.dht.message.request.PingRequest;
import com.github.bcap.dht.message.response.PingResponse;
import com.github.bcap.dht.node.Node;

public class PingRequestHandler extends RequestHandler<PingRequest, PingResponse>{

	public PingResponse handleImpl(Node node, PingRequest request) {
		return new PingResponse();
	}

}
