package com.github.bcap.dht.message;

import java.io.Serializable;
import java.util.Date;

import com.github.bcap.dht.node.Contact;

public abstract class Message implements Serializable {

	protected static final long serialVersionUID = 1L;

	private static long CONVERSATION_COUNTER = 0;

	private Contact source;
	private Contact destination;
	private long conversationId;
	private transient Date issuedDate;

	public Contact getSource() {
		return source;
	}

	public void setSource(Contact source) {
		this.source = source;
	}

	public Contact getDestination() {
		return destination;
	}

	public void setDestination(Contact destination) {
		this.destination = destination;
	}

	public long getConversationId() {
		return conversationId;
	}

	public void setConversationId(long conversationId) {
		this.conversationId = conversationId;
	}

	public Date getIssuedDate() {
		return issuedDate != null ? (Date) issuedDate.clone() : null;
	}

	public void setIssuedDate(Date issuedDate) {
		this.issuedDate = issuedDate != null ? (Date) issuedDate.clone() : null;
	}

	public static synchronized long getActualConversationId() {
		return CONVERSATION_COUNTER;
	}

	public static synchronized long getNextConversationId() {
		return CONVERSATION_COUNTER++;
	}
}
