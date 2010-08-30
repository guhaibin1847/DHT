package com.github.bcap.dht.server.handler;

import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.Response;

public abstract class RequestHandler <Req extends Request, Resp extends Response> {
	
	public Resp handle(Req request) {
		return this.handleImpl(request);
	}
	
	public abstract Resp handleImpl(Req request);
}
