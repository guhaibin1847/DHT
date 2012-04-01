package com.github.bcap.dht.message.response;

import java.io.Serializable;
import java.util.SortedMap;

public class StoreResponse extends Response implements Serializable {

	protected static final long serialVersionUID = Response.serialVersionUID + 1L;

	public StoreResponse() {
		super();
	}
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
	}
}