package me.bcap.dht.server;

public class ServerException extends Exception {

	private static final long serialVersionUID = 1L;

	public ServerException(String message) {
		super(message);
	}

	public ServerException(Exception internalException) {
		super(internalException.getMessage());
	}
}
