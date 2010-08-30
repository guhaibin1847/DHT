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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Contact;
import com.github.bcap.dht.node.Identifier;
import com.github.bcap.dht.node.Node;
import com.github.bcap.dht.server.handler.RequestHandler;
import com.github.bcap.dht.server.handler.RequestHandlerException;

public class Server extends Thread implements Runnable {

	private static final Logger logger = Logger.getLogger(Server.class);

	private static int SERVER_COUNTER = 0;
	
	private Map<Class<? extends Request>, RequestHandler> handlers;
	private Map<Identifier, Node> nodes;
	
	private InetAddress ip;
	private int port;

	private ServerSocket serverSocket;
	private ExecutorService workerThreadPool;

	private boolean hasToRun = true;
	
	public Server(InetAddress ip, int port) {
		this.ip = ip;
		this.port = port;
		this.handlers = new ConcurrentHashMap<Class<? extends Request>, RequestHandler>();
		this.nodes = new ConcurrentHashMap<Identifier, Node>();
		this.setName("Server " + SERVER_COUNTER++);
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
					Worker worker = new Worker(this.handlers, this.nodes, socket);
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
	
	class Worker implements Runnable {

		private Map<Class<? extends Request>, RequestHandler> handlersMap;
		private Map<Identifier, Node> nodesMap;
		private Socket socket;

		public Worker(Map<Class<? extends Request>, RequestHandler> handlersMap, Map<Identifier, Node> nodesMap, Socket socket) {
			this.handlersMap = handlersMap;
			this.nodesMap = nodesMap;
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
					
					logger.debug("Received request: " + request);

					Contact contact = request.getDestination();
					Node node = nodes.get(contact.asIdentifier());
					if(node == null)
						throw new IllegalArgumentException("Received request is intended for a node with id " + contact.asIdentifier() + " that is not managed by this server");

					RequestHandler handler = handlersMap.get(request.getClass());
					if(handler == null)
						throw new IllegalArgumentException("Received request cannot be handled by this server as no handler was found for type " + request.getClass());
					
					try {
						try {
							Response response = handler.handle(node, request);
							logger.debug("Writing the response object back to the client: " + response);
							outStream.writeObject(response);
						} catch (RequestHandlerException e) {
							logger.warn("RequestHandlerException occured while trying to handle the request, sending an error with same message and with no stack back to the client", e);
							outStream.writeObject(new ServerException(e));
						}
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
	
	public <T extends Request> void addHandler(Class<T> requestClass, RequestHandler<T, ? extends Response> handler) {
		this.handlers.put(requestClass, handler);
	}

	public <T extends Request> RequestHandler<T, ? extends Response> removeHandler(Class<T> requestClass) {
		return this.handlers.remove(requestClass);
	}

	public Collection<Class<? extends Request>> getHandledTypes() {
		return new ArrayList<Class<? extends Request>>(handlers.keySet());
	}
	
	public void addNode(Node node) {
		this.nodes.put(node.asIdentifier(), node);
	}

	public Node removeNode(Node node) {
		return this.nodes.remove(node.asIdentifier());
	}

	public Collection<Node> getNodes() {
		return new ArrayList<Node>(nodes.values());
	}
	
	public InetAddress getIp() {
		return ip;
	}
	
	public int getPort() {
		return port;
	}
	
	public static void main(String[] args) throws Exception {
		Server server = new Server(Inet4Address.getByName("localhost"), 50000);
		server.start();
	}

}

