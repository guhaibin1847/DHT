package com.github.bcap.dht.node;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;

import org.junit.Test;

public class NodeRefTest {

	@Test
	public void testEquals() {
		
		int[] ip1 = new int[]{200, 200, 200, 200};
		int[] ip2 = new int[]{192, 168, 1, 1};
		int port = 5000;
		
		Contact node1 = new Contact(new BigInteger("101000", 2), ip1, port);
		Contact node2 = new Contact(new BigInteger("101000", 2), ip1, port);
		Contact node3 = new Contact(new BigInteger("101000", 2), ip2, port);
		Contact node4 = new Contact(new BigInteger("101111", 2), ip1, port);

		assertTrue(node1.equals(node1));
		assertTrue(node1.equals(node2));
		assertTrue(node2.equals(node1));
		
		assertFalse(node1.equals(node3));
		assertFalse(node1.equals(node4));
		assertFalse(node3.equals(node1));
		assertFalse(node3.equals(node4));
		assertFalse(node4.equals(node1));
		assertFalse(node4.equals(node3));
		
		assertFalse(node1.equals(null));
		assertFalse(node1.equals(new Object()));
	}
}
