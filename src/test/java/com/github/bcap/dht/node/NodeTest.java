package com.github.bcap.dht.node;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class NodeTest {

	@Test
	public void testNodeDistance() {
		String bits1 = "100000000100000000000010";
		String bits2 = "000010000000010000000010";
		String bitsR = "100010000100010000000000";
		
		Node node1 = new Node(new BigInteger(bits1, 2).toByteArray());
		Node node2 = new Node(new BigInteger(bits2, 2).toByteArray());
		
		BigInteger distance = node1.getDistance(node2);
		BigInteger reverseDistance = node2.getDistance(node1);
		
		assertEquals(bitsR, distance.toString(2));
		assertEquals(distance, reverseDistance);
	}
}
