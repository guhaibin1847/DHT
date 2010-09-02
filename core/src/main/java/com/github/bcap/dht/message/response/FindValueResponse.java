package com.github.bcap.dht.message.response;

import java.io.Serializable;

import com.github.bcap.dht.message.request.FindValueRequest;

public class FindValueResponse extends Response implements Serializable {

	private static final long serialVersionUID = Response.serialVersionUID + 1L;

	public FindValueResponse(FindValueRequest request) {
		super(request);
	}

}
