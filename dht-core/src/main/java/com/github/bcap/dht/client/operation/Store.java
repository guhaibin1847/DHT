package me.bcap.dht.client.operation;

import me.bcap.dht.message.request.StoreRequest;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.message.response.StoreResponse;
import me.bcap.dht.node.Contact;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;

public class Store extends Operation<StoreResult> {

	private Identifier key;
	private byte[] data;
	private Contact destination;
	
	public Store(Node source, Contact destination, Identifier key, byte[] data) {
		super(source);
		this.destination = destination;
		this.key = key;
		this.data = data;
	}
	
	protected void executeImpl() {
		StoreRequest request = new StoreRequest();
		request.setIdentifier(key);
		request.setData(data);
		request.setDestination(destination);
		this.sendRequest(request);
	}
	
	public void handleResponse(Response response) {
		StoreResponse storeResponse = (StoreResponse) response;
		StoreResult result = new StoreResult();
		this.setResult(result);
	}

	public void handleException(Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
