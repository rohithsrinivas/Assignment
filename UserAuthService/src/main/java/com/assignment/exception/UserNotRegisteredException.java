package com.assignment.exception;

public class UserNotRegisteredException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserNotRegisteredException() {
		super();
	}

	public UserNotRegisteredException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public UserNotRegisteredException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotRegisteredException(String message) {
		super(message);
	}

	public UserNotRegisteredException(Throwable cause) {
		super(cause);
	}
	
	

}
