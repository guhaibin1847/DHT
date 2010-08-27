package com.github.bcap.dht.message;

import com.github.bcap.dht.node.Contact;

public abstract class Message {
	
	private Contact source;
	private Contact destination;
	
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
}
