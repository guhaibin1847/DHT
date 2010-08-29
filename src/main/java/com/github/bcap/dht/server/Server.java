package com.github.bcap.dht.server;

import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.server.handler.RequestHandler;

public class Server implements Runnable {

	private Map<Class<? extends Request>, RequestHandler> handlers;

	private InetAddress ip;
	private int port;

	public Server(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
		this.handlers = new ConcurrentHashMap<Class<? extends Request>, RequestHandler>();
	}

	public <T extends Request> void addHandler(Class<T> requestClass, RequestHandler<T, ? extends Response> handler) {
		this.handlers.put(requestClass, handler);
	}

	public <T extends Request> RequestHandler<T, ? extends Response> removeHandler(Class<T> requestClass) {
		return this.handlers.remove(requestClass);
	}

	public Collection<Class<? extends Request>> getHandledTypes() {
		return Collections.unmodifiableSet(handlers.keySet());
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}

}
