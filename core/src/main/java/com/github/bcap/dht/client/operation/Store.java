package com.github.bcap.dht.client.operation;

import com.github.bcap.dht.message.request.StoreRequest;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.message.response.StoreResponse;
import com.github.bcap.dht.node.Contact;
import com.github.bcap.dht.node.Identifier;

public class Store extends Operation<StoreResult> {

	private Identifier key;
	private byte[] data;
	private Contact destination;
	
	public Store(Contact source, Contact destination, Identifier key, byte[] data) {
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
