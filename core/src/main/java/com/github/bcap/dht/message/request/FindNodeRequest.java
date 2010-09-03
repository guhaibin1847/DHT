package com.github.bcap.dht.message.request;

import java.io.Serializable;

public class FindNodeRequest extends IdentifierRequest implements Serializable {

	protected static final long serialVersionUID = IdentifierRequest.serialVersionUID + 1L;

	public FindNodeRequest() {
		super();
	}

}
