package com.kramphub.springapplication.exception;

public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 2660644391455244228L;

	public ResourceNotFoundException() {
		super();
	}

	public ResourceNotFoundException(final String message) {
		super(message);
	}

}
