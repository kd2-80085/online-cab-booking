package com.app.booktaxi.customexception;

public class ResourceNotFoundException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String errMesg) {
		super(errMesg);
	}
}
