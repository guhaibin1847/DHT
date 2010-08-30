package com.github.bcap.dht.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.server.handler.RequestHandler;

public class Server extends Thread implements Runnable {

	private static final Logger logger = Logger.getLogger(Server.class);

	private static int SERVER_COUNTER = 0;
	
	private Map<Class<? extends Request>, RequestHandler> handlers;

	private InetAddress ip;
	private int port;

	private ServerSocket serverSocket;
	private ExecutorService workerThreadPool;

	private boolean hasToRun = true;
	
	public Server(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
		this.handlers = new ConcurrentHashMap<Class<? extends Request>, RequestHandler>();
		this.setName("Server " + SERVER_COUNTER++);
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
		logger.debug("Creating Handler thread pool");
		workerThreadPool = Executors.newCachedThreadPool();

		try {
			logger.info("Starting server on adddress " + ip + ":" + port);
			serverSocket = new ServerSocket(port, 50, ip);
		
			while(hasToRun) {
				try {
					Socket socket = serverSocket.accept();
					logger.debug("Incoming connection from " + socket.getInetAddress() + ":" + socket.getPort());
					Worker worker = new Worker(this.handlers, socket);
					this.workerThreadPool.submit(worker);
				} catch (IOException e) {
					//when the server is shutting down a SocketException is generated as the socket is closed
					if(!(e instanceof SocketException && e.getMessage().equals("Socket closed") && !hasToRun))
						logger.error("IOException occured while trying to accept new connections", e);
				}
			}
		
		} catch (IOException e) {
			logger.fatal("Could not create main server socket!", e);
		}
	}

	public InetAddress getIp() {
		return ip;
	}

	public int getPort() {
		return port;
	}
	
	public void shutdown() {
		logger.info("Shutting down server " + this.getName());
		this.hasToRun = false;
		if(this.serverSocket != null) {
			try {
				this.serverSocket.close();
			} catch (IOException e) {
				logger.error("IOException while trying to close the server main socket", e);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(Inet4Address.getByName("localhost"), 50000);
		server.start();
	}
}

class Worker implements Runnable {

	private static final Logger logger = Logger.getLogger(Worker.class);

	private Map<Class<? extends Request>, RequestHandler> handlersMap;
	private Socket socket;

	public Worker(Map<Class<? extends Request>, RequestHandler> handlersMap, Socket socket) {
		this.handlersMap = handlersMap;
		this.socket = socket;
	}

	@Override
	public void run() {
		ObjectInputStream inStream = null;
		ObjectOutputStream outStream = null;
		try {
			try {
				inStream = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				logger.error("IOException occured while trying to open the socket inputStream");
				throw e;
			}
			
			try {
				outStream = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				logger.error("IOException occured while trying to open the socket outputStream");
				throw e;
			}
			
			Object readObj = null;
			
			try {
				readObj = inStream.readObject();
			} catch (IOException e) {
				logger.error("IOException occured while trying to read the object from the socket");
				throw e;
			} catch (ClassNotFoundException e) {
				logger.error("ClassNotFoundException occured while trying to read object from the socket");
				throw e;
			}
			
			if(readObj instanceof Request) {
				Request request = (Request) readObj;
				RequestHandler handler = handlersMap.get(request.getClass());
				Response response = handler.handle(request);
				
				try {
					outStream.writeObject(response);
				} catch (IOException e) {
					logger.error("IOException occured while trying to write the response object back to the client");
					throw e;
				}
				
			} else {
				logger.warn("Object read from the socket is of an unsupported type (not instance of " + Request.class + "): " + readObj.getClass());
			}
				
		} catch (Exception e) {
			logger.error(null, e);
		} finally {
			closeResources(socket, inStream, outStream);
		}
	}

	private void closeResources(Socket socket, InputStream inputStream, OutputStream outputStream) {
		if (inputStream != null) {
			try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("Error while trying to close the inputstream " + inputStream, e);
			}
		}

		if (outputStream != null) {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error("Error while trying to close the outputStream " + outputStream, e);
			}
		}

		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
				logger.error("Error while trying to close the socket " + socket, e);
			}
		}
	}
}
