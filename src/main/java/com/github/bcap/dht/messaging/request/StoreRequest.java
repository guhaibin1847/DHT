package com.github.bcap.dht.messaging.request;

import java.io.Serializable;

public class StoreRequest extends IdentifierRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] data;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
