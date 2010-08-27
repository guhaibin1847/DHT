package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

public class NodeRef extends Identifier implements Serializable {

	private static final long serialVersionUID = 1L;

	private byte[] ip;
	private Integer port;

	private transient Date discoveryDate;
	private transient Date lastAliveDate;

	public NodeRef(byte[] id) {
		super(id);
	}
	
	public NodeRef(BigInteger id) {
		super(id);
	}

	public byte[] getIp() {
		return ip;
	}

	public void setIp(byte[] ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getDiscoveryDate() {
		return discoveryDate;
	}

	public void setDiscoveryDate(Date discoveryDate) {
		this.discoveryDate = discoveryDate;
	}

	public Date getLastAliveDate() {
		return lastAliveDate;
	}

	public void setLastAliveDate(Date lastAliveDate) {
		this.lastAliveDate = lastAliveDate;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof NodeRef))
			return false;

		NodeRef that = (NodeRef) obj;
		if (!Arrays.equals(this.ip, that.ip))
			return false;
		if (this.port == null) {
			if (that.port != null)
				return false;
		} else if (!this.port.equals(that.port))
			return false;

		return true;
	}

}