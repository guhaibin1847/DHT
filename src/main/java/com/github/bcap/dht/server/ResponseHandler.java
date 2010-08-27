package com.github.bcap.dht.server;

import com.github.bcap.dht.message.response.Response;

public interface ResponseHandler <T extends Response> {

	public void handleResponse(T response);
	
}
