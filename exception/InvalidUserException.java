package com.exception;

public class InvalidUserException  extends InvalidAuthException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserException() {
	}

	public InvalidUserException(String err) {
		super(err);
	}

}
