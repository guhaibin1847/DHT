package com.github.bcap.dht.server.handler;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.github.bcap.dht.message.request.FindNodeRequest;
import com.github.bcap.dht.message.response.FindNodeResponse;
import com.github.bcap.dht.node.Bucket;
import com.github.bcap.dht.node.Contact;
import com.github.bcap.dht.node.Identifier;
import com.github.bcap.dht.node.Node;

public class FindNodeRequestHandlerTest {

	private Node node;
	private InetAddress ip;
	private int port;
	private FindNodeRequest request;
	private FindNodeRequestHandler handler;
	
	private BigInteger fullBucketId;
	private BigInteger almostFullBucketId;
	private BigInteger halfFullBucketId;
	
	@Before
	public void setup() throws Exception {
		node = new Node(BigInteger.ZERO);
		ip = InetAddress.getByName("localhost");
		port = 5000;
		request = new FindNodeRequest();
		handler = new FindNodeRequestHandler();

		fullBucketId = BigInteger.ONE.shiftLeft(30);
		almostFullBucketId = BigInteger.ONE.shiftLeft(35);
		halfFullBucketId = BigInteger.ONE.shiftLeft(40);
		
		//fill full bucket
		for(Integer i = 0; i < Bucket.MAX_SIZE; i++) {
			BigInteger id = fullBucketId.add(new BigInteger(i.toString()));
			node.updateContact(new Contact(id, ip, port));
		}
		
		//fill almost full bucket
		for(Integer i = 0; i < Bucket.MAX_SIZE - 5; i++) {
			BigInteger id = almostFullBucketId.add(new BigInteger(i.toString()));
			node.updateContact(new Contact(id, ip, port));
		}
		
		//fill half full bucket
		for(Integer i = 0; i < Bucket.MAX_SIZE / 2; i++) {
			BigInteger id = halfFullBucketId.add(new BigInteger(i.toString()));
			node.updateContact(new Contact(id, ip, port));
		}
	}
	
	@Test
	public void testGetFromFullBucket() throws Exception {
		request.setIdentifier(new Identifier(fullBucketId));
		FindNodeResponse response = (FindNodeResponse) handler.handleImpl(node, request);
		Collection<Contact> contacts = response.getContacts();
		assertTrue(contacts.size() >= Bucket.MAX_SIZE);
	}
	
	@Test
	public void testGetFromAlmostFullBucket() throws Exception {
		request.setIdentifier(new Identifier(almostFullBucketId));
		FindNodeResponse response = (FindNodeResponse) handler.handleImpl(node, request);
		Collection<Contact> contacts = response.getContacts();
		assertTrue(contacts.size() >= Bucket.MAX_SIZE);
	}
	
	@Test
	public void testGetFromHalfFullBucket() throws Exception {
		request.setIdentifier(new Identifier(halfFullBucketId));
		FindNodeResponse response = (FindNodeResponse) handler.handleImpl(node, request);
		Collection<Contact> contacts = response.getContacts();
		assertTrue(contacts.size() >= Bucket.MAX_SIZE);
	}
	
	@Test
	public void testGetFromEmptyBucket() throws Exception {
		request.setIdentifier(new Identifier(BigInteger.ZERO));
		FindNodeResponse response = (FindNodeResponse) handler.handleImpl(node, request);
		Collection<Contact> contacts = response.getContacts();
		assertTrue(contacts.size() >= Bucket.MAX_SIZE);
	}
}
