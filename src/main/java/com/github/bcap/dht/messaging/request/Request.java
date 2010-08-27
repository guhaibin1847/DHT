package com.github.bcap.dht.messaging.request;

import java.util.Date;

import com.github.bcap.dht.node.Contact;

public abstract class Request {

	private static long REQUEST_COUNTER = 0;

	private long requestId;

	private Contact source;

	private transient Date issuedDate;

	public Request() {
		this.requestId = getNextId();
	}

	public Date getIssuedDate() {
		return issuedDate != null ? (Date) issuedDate.clone() : null;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate != null ? (Date) issuedDate.clone() : null;
	}

	public long getRequestId() {
		return this.requestId;
	}

	public Contact getSource() {
		return source;
	}

	public void setSource(Contact source) {
		this.source = source;
	}

	public static synchronized long getActualId() {
		return REQUEST_COUNTER;
	}

	public static synchronized long getNextId() {
		return REQUEST_COUNTER++;
	}
}
