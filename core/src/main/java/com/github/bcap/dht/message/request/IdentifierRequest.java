package com.github.bcap.dht.message.request;

import java.io.Serializable;

import com.github.bcap.dht.node.Identifier;

public abstract class IdentifierRequest extends Request implements Serializable {

	protected static final long serialVersionUID = Request.serialVersionUID + 1L;

	private Identifier identifier;

	public IdentifierRequest() {
		super();
	}
	
	public Identifier getIdentifier() {
		return identifier;
	}

	public void setIdentifier(Identifier identifier) {
		this.identifier = identifier;
	}
}
