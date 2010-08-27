package com.github.bcap.dht.node;

public class AddNodeResult {
	
	private int index;
	private boolean added;

	public AddNodeResult(int index, boolean added) {
		this.index = index;
		this.added = added;
	}

	public int getIndex() {
		return index;
	}

	public boolean isAdded() {
		return added;
	}
}