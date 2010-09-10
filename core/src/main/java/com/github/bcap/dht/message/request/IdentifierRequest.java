package com.github.bcap.dht.message.request;

import java.io.Serializable;
import java.util.SortedMap;

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
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
		propertiesMap.put("identifier", identifier);
	}
}
