package me.bcap.dht.server.handler;

import me.bcap.dht.message.request.Request;
import me.bcap.dht.message.request.StoreRequest;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.message.response.StoreResponse;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;

public class StoreRequestHandler extends RequestHandler {

	@Override
	public Response handleImpl(Node node, Request request) {
		StoreRequest store = (StoreRequest) request;
		Identifier key = store.getIdentifier();
		byte[] data = store.getData();
		node.getDataStorage().put(key, data);
		return new StoreResponse();
	}

}
