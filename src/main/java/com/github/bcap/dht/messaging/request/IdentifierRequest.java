package com.github.bcap.dht.messaging.request;

import com.github.bcap.dht.node.Identifier;

public abstract class IdentifierRequest extends Request {

	private Identifier identifier;

	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
}
