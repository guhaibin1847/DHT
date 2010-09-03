package com.github.bcap.dht.message.response;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import com.github.bcap.dht.node.Contact;

public class FindNodeResponse extends Response implements Serializable {

	protected static final long serialVersionUID = Response.serialVersionUID + 1L;

	private Collection<Contact> contacts = new HashSet<Contact>();
	
	public FindNodeResponse() {
		super();
	}
	
	public Collection<Contact> getContacts() {
		return this.contacts;
	}
}
