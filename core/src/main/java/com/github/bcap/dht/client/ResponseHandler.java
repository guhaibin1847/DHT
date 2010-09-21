package com.github.bcap.dht.client;

import com.github.bcap.dht.message.response.Response;

public interface ResponseHandler {

	public void handleResponse(Response response);
	
	public void handleException(Exception exception);
	
}
