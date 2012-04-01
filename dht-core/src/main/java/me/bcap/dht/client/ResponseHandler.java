package me.bcap.dht.client;

import me.bcap.dht.message.response.Response;

public interface ResponseHandler {

	public void handleResponse(Response response);
	
	public void handleException(Exception exception);
	
}
