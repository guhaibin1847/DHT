package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bucket extends Identifier implements Serializable, Iterable<NodeRef> {

	private static final long serialVersionUID = 1L;

	public static final int MAX_SIZE = 20;
	
	private List<NodeRef> nodes; 
	
	public Bucket(BigInteger id) {
		super(id);
		this.nodes = new ArrayList<NodeRef>();
	}
	
	public int size() {
		return nodes.size();
	}
	
	public void addNode(NodeRef node) throws IllegalStateException {
		if(this.size() >= MAX_SIZE)
			throw new IllegalStateException("Cannot add, bucket at max capacity of " + MAX_SIZE);
		else
			nodes.add(node);
	}
	
	public boolean removeNode(NodeRef node) {
		return this.nodes.remove(node);
	}

	public Iterator<NodeRef> iterator() {
		return nodes.iterator();
	}
}