package me.bcap.dht.server.handler;

import me.bcap.dht.message.request.FindValueRequest;
import me.bcap.dht.message.request.Request;
import me.bcap.dht.message.response.FindValueResponse;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;

public class FindValueRequestHandler extends FindNodeRequestHandler {

	public Response handleImpl(Node node, Request request) {
		FindValueRequest findValue = (FindValueRequest) request;
		Identifier key = findValue.getIdentifier();
		byte[] data = node.getDataStorage().get(key);
		if (data != null) {
			FindValueResponse response = new FindValueResponse();
			response.setData(data);
			return response;
		} else {
			return super.handleImpl(node, request);
		}
	}

}
