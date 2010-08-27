package com.github.bcap.dht.message.response;

import java.io.Serializable;

import com.github.bcap.dht.message.request.PingRequest;

public class PingResponse extends Response implements Serializable {

	private static final long serialVersionUID = 1L;

	public PingResponse(PingRequest request) {
		super(request);
	}

}
