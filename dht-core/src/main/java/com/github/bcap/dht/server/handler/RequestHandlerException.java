package com.github.bcap.dht.server.handler;

public class RequestHandlerException extends Exception {

	private static final long serialVersionUID = 1L;

	public RequestHandlerException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestHandlerException(String message) {
		super(message);
	}
}
