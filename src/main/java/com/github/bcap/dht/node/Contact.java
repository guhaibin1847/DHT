package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;

public class Contact extends Identifier implements Serializable {

	private static final long serialVersionUID = 1L;

	private int[] ip;
	private Integer port;

	private Date discoveryDate;
	private Date lastAliveDate;

	protected Contact(){
		this.resetDates();
	}
	
	public Contact(BigInteger id, int[] ip, int port) {
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

	public int[] getIp() {
		return this.ip != null ? Arrays.copyOf(this.ip, this.ip.length) : null;
	}

	public void setIp(int[] ip) {
		this.ip = ip != null ? Arrays.copyOf(ip, ip.length) : null;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getDiscoveryDate() {
		return discoveryDate != null ? (Date)discoveryDate.clone() : null;
	}

	public void setDiscoveryDate(Date discoveryDate) {
		this.discoveryDate = discoveryDate != null ? (Date)discoveryDate.clone() : null;
	}

	public Date getLastAliveDate() {
		return lastAliveDate != null ? (Date)lastAliveDate.clone() : null;
	}

	public void setLastAliveDate(Date lastAliveDate) {
		this.lastAliveDate = lastAliveDate != null ? (Date)lastAliveDate.clone() : null;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof Contact))
			return false;

		Contact that = (Contact) obj;
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