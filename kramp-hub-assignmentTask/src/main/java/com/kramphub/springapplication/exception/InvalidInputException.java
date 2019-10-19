package com.kramphub.springapplication.exception;

public class InvalidInputException extends Exception {


	private static final long serialVersionUID = 994957028659532943L;

	public InvalidInputException() {
		super();
	}

	public InvalidInputException(final String message) {
		super(message);
	}

}
