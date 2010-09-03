package com.github.bcap.dht.message.response;

import java.io.Serializable;

public class FindValueResponse extends FindNodeResponse implements Serializable {

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
}
