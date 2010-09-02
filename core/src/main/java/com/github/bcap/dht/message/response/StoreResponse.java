package com.github.bcap.dht.message.response;

import java.io.Serializable;

import com.github.bcap.dht.message.request.StoreRequest;

public class StoreResponse extends Response implements Serializable {

	private static final long serialVersionUID = Response.serialVersionUID + 1L;

	public StoreResponse(StoreRequest request) {
		super(request);
	}

}