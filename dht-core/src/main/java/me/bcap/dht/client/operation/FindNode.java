package me.bcap.dht.client.operation;

import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;

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
