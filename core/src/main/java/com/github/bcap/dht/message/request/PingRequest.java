package com.github.bcap.dht.message.request;

import java.io.Serializable;

public class PingRequest extends Request implements Serializable {

	protected static final long serialVersionUID = Request.serialVersionUID + 1L;

	public PingRequest() {
		super();
	}
}
