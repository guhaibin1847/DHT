package me.bcap.dht.client.operation;

import me.bcap.dht.message.request.PingRequest;
import me.bcap.dht.message.response.PingResponse;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Contact;
import me.bcap.dht.node.Node;

public class Ping extends Operation<PingResult> {

	private Contact destination;

	public Ping(Node source, Contact destination) {
		super(source);
		this.destination = destination;
	}

	protected void executeImpl() {
		PingRequest request = new PingRequest();
		request.setDestination(destination);
		this.sendRequest(request);
	}
	
	public void handleResponse(Response response) {
		PingResponse pingResponse = (PingResponse) response;
		PingResult result = new PingResult();
		this.setResult(result);
	}

	public void handleException(Exception exception) {
		// TODO Auto-generated method stub
	}

}
