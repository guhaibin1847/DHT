package com.github.bcap.dht.node;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Before;
import org.junit.Test;

public class BucketTest {
	
	private Bucket bucket;
	private NodeRef node;
	
	@Before
	public void setUp() {
		bucket = new Bucket(BigInteger.ZERO);
		node = new NodeRef(BigInteger.ZERO);
	}
	
	@Test
	public void testAddNode() {
		assertEquals(0, bucket.size());

		for(int i = 1; i <= Bucket.MAX_SIZE; i++) {
			bucket.addNode(node);
			assertEquals(i, bucket.size());
		}
		
		try {
			bucket.addNode(node);
			fail("bucket addition should fail");
		} catch (IllegalStateException e) {
			assertEquals(Bucket.MAX_SIZE, bucket.size());
		}
	}
	
	@Test
	public void testBucketIteration() {
		assertFalse(bucket.iterator().hasNext());

		for(int i = 1; i <= Bucket.MAX_SIZE; i++) {
			bucket.addNode(node);
			int iterated = 0;
			for (NodeRef iteratedNode : bucket)
				iterated++;
			assertEquals(i, iterated);
		}
	}
}
