package com.github.bcap.dht.server.handler;

import java.util.ArrayList;
import java.util.List;

import com.github.bcap.dht.message.request.IdentifierRequest;
import com.github.bcap.dht.message.request.Request;
import com.github.bcap.dht.message.response.FindNodeResponse;
import com.github.bcap.dht.message.response.Response;
import com.github.bcap.dht.node.Bucket;
import com.github.bcap.dht.node.Contact;
import com.github.bcap.dht.node.Identifier;
import com.github.bcap.dht.node.Node;

public class FindNodeRequestHandler extends RequestHandler {

	public Response handleImpl(Node node, Request request) {
		IdentifierRequest idReq = (IdentifierRequest) request;
		int nodeIndex = node.getBucketIndex(node);
		int bucketIndex = node.getBucketIndex(idReq.getIdentifier());
		//Direction tells us the direction where to move to get closer to the node itself
		//Eventually if the buckets are not full the window will keep moving an getting nodes
		//from another buckets, warping aroung the edges of the buckets array
		int direction = nodeIndex > bucketIndex ? 1 : -1;
		
		FindNodeResponse response = new FindNodeResponse();
		
		List<Contact> contacts = new ArrayList<Contact>();
		
		for(int visitedBuckets = 0; contacts.size() < Bucket.MAX_SIZE && visitedBuckets < Identifier.LENGTH; visitedBuckets++) {
			Bucket bucket = node.getBucket(bucketIndex);
			contacts.addAll(bucket.getContacts());
			bucketIndex = circularMod(bucketIndex + direction, Identifier.LENGTH);
		}
		
		response.setContacts(contacts);
		
		return response;
	}
	
	private int circularMod(int x, int y) {
		return ((x % y) + y ) % y;
	}
}
