package com.exception;

public class InvalidAuthException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidAuthException() {
		super();
	}

	public InvalidAuthException(String err) {
		super(err);
		
	}
}
