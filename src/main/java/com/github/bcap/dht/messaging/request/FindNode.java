package com.github.bcap.dht.messaging.request;

import java.io.Serializable;

import com.github.bcap.dht.node.Contact;
import com.github.bcap.dht.node.Identifier;

public class FindNode extends Request implements Serializable {

	private static final long serialVersionUID = 1L;

	private Contact source;
	
	private Identifier query;
	
}
