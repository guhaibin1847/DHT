package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.net.InetAddress;
import java.util.Date;

public class Contact extends Identifier implements Serializable {

	private static final long serialVersionUID = 1L;

	private InetAddress ip;
	private Integer port;

	private Date discoveryDate;
	private Date lastAliveDate;

	protected Contact() {
		this.resetDates();
	}

	public Contact(BigInteger id, InetAddress ip, int port) {
		super(id);
		this.ip = ip;
		this.port = port;
		this.resetDates();
	}

	public void alive() {
		this.setLastAliveDate(new Date());
	}

	public void resetDates() {
		this.discoveryDate = new Date();
		this.lastAliveDate = new Date();
	}

	public InetAddress getIp() {
		return ip;
	}

	public void setIp(InetAddress ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getDiscoveryDate() {
		return discoveryDate != null ? (Date) discoveryDate.clone() : null;
	}

	public void setDiscoveryDate(Date discoveryDate) {
		this.discoveryDate = discoveryDate != null ? (Date) discoveryDate.clone() : null;
	}

	public Date getLastAliveDate() {
		return lastAliveDate != null ? (Date) lastAliveDate.clone() : null;
	}

	public void setLastAliveDate(Date lastAliveDate) {
		this.lastAliveDate = lastAliveDate != null ? (Date) lastAliveDate.clone() : null;
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		Contact other = (Contact) obj;
		if (discoveryDate == null) {
			if (other.discoveryDate != null)
				return false;
		} else if (!discoveryDate.equals(other.discoveryDate))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (lastAliveDate == null) {
			if (other.lastAliveDate != null)
				return false;
		} else if (!lastAliveDate.equals(other.lastAliveDate))
			return false;
		if (port == null) {
			if (other.port != null)
				return false;
		} else if (!port.equals(other.port))
			return false;
		return true;
	}

}