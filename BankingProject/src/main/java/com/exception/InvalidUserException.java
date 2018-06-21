package com.exception;

public class InvalidUserException  extends InvalidAuthException{

	public InvalidUserException() {
	}

	public InvalidUserException(String err) {
		super(err);
	}

}
