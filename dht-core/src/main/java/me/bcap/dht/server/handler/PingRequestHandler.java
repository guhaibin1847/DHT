package me.bcap.dht.server.handler;

import me.bcap.dht.message.request.Request;
import me.bcap.dht.message.response.PingResponse;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Node;

public class PingRequestHandler extends RequestHandler {

	public Response handleImpl(Node node, Request request) {
		return new PingResponse();
	}

}
