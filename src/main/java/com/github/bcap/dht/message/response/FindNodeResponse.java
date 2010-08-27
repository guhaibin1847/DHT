package com.github.bcap.dht.message.response;

import java.io.Serializable;

import com.github.bcap.dht.message.request.FindNodeRequest;

public class FindNodeResponse extends Response implements Serializable {

	private static final long serialVersionUID = 1L;

	public FindNodeResponse(FindNodeRequest request) {
		super(request);
	}
}
