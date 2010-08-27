package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Node extends NodeRef implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int BUCKET_SIZE = 20;
	
	private transient List<NodeRef>[] buckets;
	
	public Node(BigInteger id) {
		super(id);
		createBuckets();
	}
	
	public Node(byte[] id) {
		super(id);
		createBuckets();
	}
	
	public AddNodeResult addNode(NodeRef node) {
		boolean added;
		int index = getBucketIndex(node);
		if(added = buckets[index].size() < BUCKET_SIZE)
			buckets[index].add(node);
		return new AddNodeResult(index, added);
	}
	
	protected int getBucketIndex(Identifier id) {
		BigInteger distance = this.getDistance(id);
		return distance.bitLength();
	}
	
	private void createBuckets() {
		this.buckets = new List[LENGTH];
		for (int i = 0; i < buckets.length; i++)
			this.buckets[i] = new ArrayList<NodeRef>();
	}
	
}
