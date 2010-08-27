package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;

public class Node extends Identifier implements Serializable {

	private static final long serialVersionUID = 1L;

	private Bucket[] buckets;
	
	public Node() {
		createBuckets();
	}
	
	public Node(BigInteger value) {
		super(value);
		createBuckets();
	}
	
	public int getBucketIndex(Identifier id) {
		int index = id.getValue().bitLength();
		if(index > 0)
			index--;
		return index;
	}
	
	public Bucket getBucket(int index) {
		return buckets[index];
	}
	
	public Bucket getBucketForId(Identifier id) {
		return getBucket(getBucketIndex(id));
	}
	
	private void createBuckets() {
		BigInteger bucketId = BigInteger.ONE;
		this.buckets = new Bucket[LENGTH];
		for (int i = 0; i < buckets.length; i++) {
			this.buckets[i] = new Bucket(bucketId);
			bucketId = bucketId.shiftLeft(1);
		}
	}
}