package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;

public class Identifier implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public static final int LENGTH = 128;
	
	private BigInteger value;

	protected Identifier() {}
	
	public Identifier(BigInteger value) {
		this.value = value;
	}
	
	public BigInteger getValue() {
		return this.value;
	}
	
	public BigInteger getDistance(Identifier anotherId) {
		return this.value.xor(anotherId.value);
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
	
	public boolean equals(Object obj) {
		if(obj instanceof Identifier) {
			Identifier that = (Identifier) obj;
			if(that == null)
				return false;
			else if (this == that)
				return true;
			else 
				return this.value.equals(that.value);
		} else {
			return false;
		}
	}
	
	
}
