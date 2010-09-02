package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Node extends Identifier implements Serializable {

	private static final long serialVersionUID = 1L;

	private Bucket[] buckets;
	
	private Map<Identifier, byte[]> dataStorage;
	
	protected Node() {
		init();
	}
	
	public Node(BigInteger value) {
		super(value);
		init();
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
	
	private void init() {
		createBuckets();
		createDataStorage();
	}

	private void createBuckets() {
		BigInteger bucketId = BigInteger.ONE;
		this.buckets = new Bucket[LENGTH];
		for (int i = 0; i < buckets.length; i++) {
			this.buckets[i] = new Bucket(bucketId);
			bucketId = bucketId.shiftLeft(1);
		}
	}
	
	private void createDataStorage() {
		this.dataStorage = new ConcurrentHashMap<Identifier, byte[]>();
	}

	public Map<Identifier, byte[]> getDataStorage() {
		return dataStorage;
	}

	public void setDataStorage(Map<Identifier, byte[]> dataStorage) {
		this.dataStorage = dataStorage;
	}
}