package me.bcap.dht.message.response;

import java.io.Serializable;
import java.util.Collection;
import java.util.SortedMap;

import me.bcap.dht.node.Contact;

public class FindNodeResponse extends Response implements Serializable {

	protected static final long serialVersionUID = Response.serialVersionUID + 1L;

	private Collection<Contact> contacts;
	
	public FindNodeResponse() {
		super();
	}
	
	public Collection<Contact> getContacts() {
		return this.contacts;
	}
	
	public void setContacts(Collection<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public void addToStringProperties(SortedMap<String, Object> propertiesMap) {
		super.addToStringProperties(propertiesMap);
		propertiesMap.put("contacts.size", contacts != null ? contacts.size() : "null");
	}
}

