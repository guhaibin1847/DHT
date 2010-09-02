package com.github.bcap.dht.message.response;

import java.io.Serializable;

import com.github.bcap.dht.message.Message;

public abstract class Response extends Message implements Serializable {

	protected static final long serialVersionUID = Message.serialVersionUID + 1L;

	public Response() {
		super();
	}
}
