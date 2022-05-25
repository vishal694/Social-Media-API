package com.example.demo.exception;

public class ApiException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7022562489001697920L;

	public ApiException() {
		super();
	}

	public ApiException(String message) {
		super(message);
	}
}
