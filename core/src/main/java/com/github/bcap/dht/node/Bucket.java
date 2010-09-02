package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bucket extends Identifier implements Serializable, Iterable<Contact> {

	private static final long serialVersionUID = 1L;

	public static final int MAX_SIZE = 20;

	private Map<Identifier, Contact> contacts = new HashMap<Identifier, Contact>();

	protected Bucket() {
	}

	public Bucket(BigInteger id) {
		super(id);
	}

	public int size() {
		return contacts.size();
	}

	public boolean updateContact(Contact contact) {
		Identifier key = contact.asIdentifier();
		if (contacts.containsKey(key) || this.size() < MAX_SIZE) {
			contacts.put(key, contact);
			return true;
		} else {
			return false;
		}
	}

	public boolean removeContact(Contact contact) {
		return this.contacts.remove(contact.asIdentifier()) != null;
	}
	
	public Contact getContact(Identifier id) {
		return contacts.get(id.asIdentifier());
	}

	public Iterator<Contact> iterator() {
		return contacts.values().iterator();
	}
}