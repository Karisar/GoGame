package com.sarsila.model.dao;

public class ConnectionException extends Exception {
	public ConnectionException() {
		super();
	}
	
	public ConnectionException(String reason) {
		super(reason);
	}
}
