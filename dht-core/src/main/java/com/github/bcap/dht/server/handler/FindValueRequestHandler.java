package com.github.bcap.dht.server.handler;

import com.github.bcap.dht.message.request.FindValueRequest;
import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.FindValueResponse;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Identifier;
import com.github.bcap.dht.node.Node;

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
