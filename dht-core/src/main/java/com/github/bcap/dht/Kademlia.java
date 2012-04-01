package me.bcap.dht;

import java.math.BigInteger;
import java.net.InetAddress;

import me.bcap.dht.client.ConcurrentMessageSender;
import me.bcap.dht.client.ResponseHandler;
import me.bcap.dht.message.request.FindNodeRequest;
import me.bcap.dht.message.response.Response;
import me.bcap.dht.node.Contact;
import me.bcap.dht.node.Identifier;
import me.bcap.dht.node.Node;
import me.bcap.dht.server.Server;

public class Kademlia {
	public static void main(String[] args) throws Exception {
		BigInteger id = BigInteger.ONE.shiftLeft(3);
		InetAddress ip = InetAddress.getLocalHost();
		Integer port = 5000;
		
		Node serverNode = new Node(id, ip, port);
		Contact serverContact = serverNode.asContact();
		Contact clientContact = new Contact(id.shiftLeft(1), ip, port);

		final Server server = new Server(ip, port);
		server.addDefaultHandlers();
		server.addNode(serverNode);
		server.start();
		
		final ConcurrentMessageSender sender = new ConcurrentMessageSender(3);
		FindNodeRequest request = new FindNodeRequest();
		request.setDestination(serverContact);
		request.setSource(clientContact);
		request.setIdentifier(new Identifier(BigInteger.ONE));
		sender.send(request, new ResponseHandler() {
			public void handleResponse(Response response) {
				System.out.println(response);
				server.shutdown();
				sender.shutdown();
			}

			public void handleException(Exception exception) {
			}
		});
	}
}