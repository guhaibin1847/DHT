package me.bcap.dht.message;

import java.io.Serializable;
import java.util.Date;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Map.Entry;

import me.bcap.dht.node.Contact;
import me.bcap.dht.util.ToStringFriendly;

public abstract class Message implements Serializable, ToStringFriendly {

	protected static final long serialVersionUID = 1L;

	private static long CONVERSATION_COUNTER = 0;

	private Contact source;
	private Contact destination;
	private long conversationId;
	private transient Date issuedDate;
	

	public String toString() {
		final String separator = ", ";

		StringBuilder builder = new StringBuilder("[");
		builder.append("type: ");
		builder.append(this.getClass().getSimpleName());
		builder.append(separator);
		
		SortedMap<String, Object> map = new TreeMap<String, Object>();
		this.addToStringProperties(map);
		
		for (Entry<String, Object> entry: map.entrySet()) {
			builder.append(entry.getKey());
			builder.append(": ");
			builder.append(entry.getValue());
			builder.append(separator);
		}
		builder.delete(builder.length() - separator.length(), builder.length());
		
		builder.append("]");
		
		return builder.toString();
	}
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		propertiesMap.put("source", source);
		propertiesMap.put("destination", destination);
		propertiesMap.put("conversationId", conversationId);
		propertiesMap.put("issuedDate", issuedDate);
	}
	
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
