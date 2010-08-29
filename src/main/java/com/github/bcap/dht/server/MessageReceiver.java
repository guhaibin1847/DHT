package com.github.bcap.dht.server;

import com.github.bcap.dht.message.request.Request;

public interface MessageReceiver {

	public void receive(Request request);

}
