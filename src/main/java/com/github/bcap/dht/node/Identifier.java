package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;

public class Identifier implements Serializable, Comparable<Identifier> {
	
	private static final long serialVersionUID = 1L;

	public static final int LENGTH = 128;
	
	private BigInteger id;

	protected Identifier(byte[] id) {
		this(new BigInteger(1, id));
	}
	
	protected Identifier(BigInteger id) {
		this.id = id;
	}
	
	public BigInteger getValue() {
		return this.id;
	}
	
	public BigInteger getDistance(Identifier anotherId) {
		return this.id.xor(anotherId.id);
	}
	
	public <T extends Identifier> T getClosest(Collection<T> identifiers) {
		T result = null;
		BigInteger lesserDistance = null;
		if(identifiers != null) {
			for (T node : identifiers) {
				BigInteger distance = this.getDistance(node);
				if(result == null || distance.compareTo(lesserDistance) < 0) {
					result = node;
					lesserDistance = distance;
				}
			}
		}
		return result;
	}
	
	public int compareTo(Identifier that) {
		if(that == null)
			return -1;
		else if (this.equals(that))
			return 0;
		else 
			return this.id.compareTo(that.id);
	}
	
	public boolean equals(Object obj) {
		if(obj instanceof Identifier) {
			Identifier that = (Identifier) obj;
			if(that == null)
				return false;
			else if (this == that)
				return true;
			else 
				return this.id.equals(that.id);
		} else {
			return false;
		}
	}
}
