package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;

public class Node extends NodeRef implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient Bucket[] buckets;
	
	public Node(BigInteger id) {
		super(id);
		createBuckets();
	}
	
	public Node(byte[] id) {
		super(id);
		createBuckets();
	}
	
	public int getBucketIndex(Identifier id) {
		return this.getDistance(id).bitLength();
	}
	
	public Bucket getBucket(int index) {
		return buckets[index];
	}
	
	public Bucket getBucketForId(Identifier id) {
		return getBucket(getBucketIndex(id));
	}
	
	private void createBuckets() {
		this.buckets = new Bucket[LENGTH];
		for (int i = 0; i < buckets.length; i++)
			this.buckets[i] = new Bucket();
	}
	
}
