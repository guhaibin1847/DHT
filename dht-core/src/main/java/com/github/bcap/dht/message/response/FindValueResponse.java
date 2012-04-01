package me.bcap.dht.message.response;

import java.io.Serializable;
import java.util.SortedMap;

public class FindValueResponse extends Response implements Serializable {

	protected static final long serialVersionUID = FindNodeResponse.serialVersionUID + 1L;

	private byte[] data;
	
	public FindValueResponse() {
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
