package com.github.bcap.dht.node;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

public class Bucket extends Identifier implements Serializable, Iterable<Contact> {

	private static final long serialVersionUID = 1L;

	public static final int MAX_SIZE = 20;
	
	private SortedSet<Contact> contacts = new TreeSet<Contact>(new AliveTimeComparator());
	
	protected Bucket() {}
	
	public Bucket(BigInteger id) {
		super(id);
	}
	
	public int size() {
		return contacts.size();
	}
	
	public void addContact(Contact contact) throws IllegalStateException {
		if(!contacts.contains(contact) && this.size() >= MAX_SIZE)
			throw new IllegalStateException("Cannot add, bucket at max capacity of " + MAX_SIZE);
		else
			contacts.add(contact);
	}
	
	public boolean removeContact(Contact contact) {
		return this.contacts.remove(contact);
	}
	
	public Iterator<Contact> iterator() {
		return contacts.iterator();
	}
}

class AliveTimeComparator implements Comparator<Contact> {

	public int compare(Contact contact1, Contact contact2) {
		return contact1.getLastAliveDate().compareTo(contact2.getLastAliveDate());
	}
}