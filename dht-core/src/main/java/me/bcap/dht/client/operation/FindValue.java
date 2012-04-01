package me.bcap.dht.client.operation;

import me.bcap.dht.message.request.FindValueRequest;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;

public class FindValue extends Operation<FindValueResult> {

	private Identifier key;

	public FindValue(Node source, Identifier key) {
		super(source);
		this.key = key;
	}

	@Override
	protected void executeImpl() {
		FindValueRequest request = new FindValueRequest();
		request.setIdentifier(key);
	}
	
	@Override
	public void handleResponse(Response response) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void handleException(Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
