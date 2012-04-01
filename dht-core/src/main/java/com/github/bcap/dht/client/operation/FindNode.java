package com.github.bcap.dht.client.operation;

import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Identifier;
import com.github.bcap.dht.node.Node;

public class FindNode extends Operation<FindNodeResult> {

	private Identifier key;
	
	public FindNode(Node source, Identifier key) {
		super(source);
		this.key = key;
	}
	
	protected void executeImpl() {
		// TODO Auto-generated method stub
		
	}

	public void handleResponse(Response response) {
		// TODO Auto-generated method stub
		
	}

	public void handleException(Exception exception) {
		// TODO Auto-generated method stub
		
	}

}
