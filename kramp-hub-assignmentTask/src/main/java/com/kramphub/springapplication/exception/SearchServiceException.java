package com.kramphub.springapplication.exception;

public class SearchServiceException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public SearchServiceException() {
		super();
	}

	public SearchServiceException(final String message) {
		super(message);
	}
}