package com.intendentapp.exceptions;

public class NoFindCardException extends RuntimeException {
	
	public NoFindCardException() {}
	
	public NoFindCardException(String message) {
		super(message);
	}
	
}
