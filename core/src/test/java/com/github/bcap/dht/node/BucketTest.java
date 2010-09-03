package com.github.bcap.dht.node;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BucketTest {
	
	private Bucket bucket;
	private Contact node;
	private InetAddress ip;
	private int port;
	
	@Before
	public void setUp() throws UnknownHostException {
		bucket = new Bucket(BigInteger.ZERO);
		ip = Inet4Address.getByName("127.0.0.1");
		node = new Contact(BigInteger.ONE, ip, port);
	}
	
	@Test
	public void testUpdateContactAdd() {
		assertEquals(0, bucket.size());

		for(int i = 1; i <= Bucket.MAX_SIZE; i++) {
			assertTrue(bucket.updateContact(createContact(i)));
			assertEquals(i, bucket.size());
		}
		
		assertFalse(bucket.updateContact(createContact(1000)));
		assertEquals(Bucket.MAX_SIZE, bucket.size());
	}
	
	@Test
	public void testUpdateContactUpdate() {
		Contact contact = createContact(5);

		assertEquals(0, bucket.size());
		
		assertTrue(bucket.updateContact(contact));
		assertEquals(1, bucket.size());
		
		assertTrue(bucket.updateContact(contact));;
		assertEquals(1, bucket.size());
		
		contact.setPort(port + 1);
		
		assertTrue(bucket.updateContact(contact));;
		assertEquals(1, bucket.size());
		
		Contact newContact = new Contact(contact.getValue(), contact.getIp(), contact.getPort());
		assertTrue(bucket.updateContact(newContact));
		assertEquals(1, bucket.size());
	}
	
	@Test 
	public void testGetContact() {
		Contact contact = createContact(5);
		bucket.updateContact(contact);
		
		assertEquals(contact, bucket.getContact(contact));
		assertEquals(contact, bucket.getContact(contact.asIdentifier()));
		assertEquals(contact, bucket.getContact(new Identifier(contact.getValue())));
		assertEquals(contact, bucket.getContact(new Contact(contact.getValue(), ip, port + 1)));
		assertEquals(contact, bucket.getContact(new Node(contact.getValue())));
		
		assertNull(bucket.getContact(new Identifier(contact.getValue().shiftLeft(1))));
	}
	
	@Test
	public void testBucketIteration() {
		assertFalse(bucket.iterator().hasNext());

		for(int i = 1; i <= Bucket.MAX_SIZE; i++) {
			bucket.updateContact(createContact(i));
			int iterated = 0;
			for (Contact iteratedNode : bucket)
				iterated++;
			assertEquals(i, iterated);
		}
	}
	
	private Contact createContact(int i) {
		Contact contact = new Contact(node.getValue().shiftLeft(i), ip, port);
		Date date = new Date();
		date.setTime(i);
		contact.setLastAliveDate(date);
		contact.setDiscoveryDate(date);
		return contact;
	}
}
