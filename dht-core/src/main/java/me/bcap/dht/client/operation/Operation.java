package me.bcap.dht.client.operation;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.apache.log4j.Logger;

import me.bcap.dht.client.MessageSender;
import me.bcap.dht.client.ResponseHandler;
import me.bcap.dht.message.request.Request;
import me.bcap.dht.node.Node;

public abstract class Operation <T extends OperationResult> implements ResponseHandler {

	private static final Logger logger = Logger.getLogger(Operation.class);

	protected MessageSender messageServer;

	private T result;
	private CountDownLatch resultLatch = new CountDownLatch(1);

	private Node source;
	
	public Operation(Node source) {
		this.source = source;
	}
	
	public Future<T> execute() {
		this.executeImpl();
		return new FutureTask<T>(new CallableResult(this));
	}
	
	protected abstract void executeImpl();
	
	protected T getResult() {
		try {
			resultLatch.await();
		} catch (InterruptedException e) {
			logger.warn("Result latch was interrupted, the result may not be ready yet (may be null)");
		}
		return result;
	}
	
	protected void setResult(T result) {
		this.result = result;
		resultLatch.countDown();
	}

	protected void sendRequest(Request request) {
		request.setSource(source.asContact());
		messageServer.send(request, this);
	}

	public MessageSender getMessageServer() {
		return messageServer;
	}

	public void setMessageServer(MessageSender server) {
		this.messageServer = server;
	}
}

class CallableResult<T extends OperationResult> implements Callable<T> {

	private Operation<T> operation;
	
	public CallableResult(Operation<T> operation) {
		this.operation = operation;
	}

	public T call() throws Exception {
		return operation.getResult();
	}
}