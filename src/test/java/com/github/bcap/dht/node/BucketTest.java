package com.github.bcap.dht.node;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class BucketTest {
	
	private Bucket bucket;
	private Contact node;
	private int[] ip;
	private int port;
	
	@Before
	public void setUp() {
		bucket = new Bucket(BigInteger.ZERO);
		ip = new int[] {192, 168, 1, 1 };
		node = new Contact(BigInteger.ONE, ip, port);
	}
	
	@Test
	public void testAddNode() {
		assertEquals(0, bucket.size());

		for(int i = 1; i <= Bucket.MAX_SIZE; i++) {
			bucket.addContact(createContact(i));
			assertEquals(i, bucket.size());
		}
		
		try {
			bucket.addContact(createContact(1000));
			fail("bucket addition should fail");
		} catch (IllegalStateException e) {
			assertEquals(Bucket.MAX_SIZE, bucket.size());
		}
	}
	
	@Test
	public void testBucketIteration() {
		assertFalse(bucket.iterator().hasNext());

		for(int i = 1; i <= Bucket.MAX_SIZE; i++) {
			bucket.addContact(createContact(i));
			int iterated = 0;
			for (Contact iteratedNode : bucket)
				iterated++;
			assertEquals(i, iterated);
		}
	}
	
	private Contact createContact(int i) {
		Contact contact = new Contact(node.getValue().shiftLeft(1), ip, port);
		Date date = new Date();
		date.setTime(i);
		contact.setLastAliveDate(date);
		contact.setDiscoveryDate(date);
		return contact;
	}
}
