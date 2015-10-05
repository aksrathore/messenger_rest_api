package org.abhinav.rest.messenger.exception;

public class DataNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3230585523973281768L;

	public DataNotFoundException(String message) {
		super(message);
	}

}
