package com.github.bcap.dht.message.request;

import java.io.Serializable;

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
}
