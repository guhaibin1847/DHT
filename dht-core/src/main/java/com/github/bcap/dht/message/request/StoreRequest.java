package com.github.bcap.dht.message.request;

import java.io.Serializable;
import java.util.SortedMap;

public class StoreRequest extends IdentifierRequest implements Serializable {

	protected static final long serialVersionUID = IdentifierRequest.serialVersionUID + 1L;

	private byte[] data;

	public StoreRequest() {
		super();
	}
	
	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
		propertiesMap.put("data.length", data != null ? data.length : "null");
	}
}
