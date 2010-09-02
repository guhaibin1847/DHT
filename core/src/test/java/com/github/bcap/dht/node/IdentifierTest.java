package com.github.bcap.dht.node;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class IdentifierTest {
	
	@Test
	public void testEquals() {
		Identifier id1 = new Identifier(new BigInteger("101000", 2));
		Identifier id2 = new Identifier(new BigInteger("00101000", 2));
		Identifier id3 = new Identifier(new BigInteger("101111", 2));

		assertTrue(id1.equals(id2));
		assertTrue(id2.equals(id1));
		
		assertFalse(id1.equals(id3));
		assertFalse(id2.equals(id3));
		assertFalse(id3.equals(id1));
		assertFalse(id3.equals(id2));
		
		assertFalse(id1.equals(null));
		assertFalse(id1.equals(new Object()));
	}
}
