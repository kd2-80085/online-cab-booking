package com.app.booktaxi.customexception;

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String errMesg) {
		super(errMesg);
	}
}
