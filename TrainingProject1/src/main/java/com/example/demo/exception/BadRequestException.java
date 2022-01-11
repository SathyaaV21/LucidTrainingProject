package com.example.demo.exception;

public class BadRequestException extends RuntimeException {

	/**
	 * Method to Route the process to corresponding handler to get the complete
	 * response
	 */
	private static final long serialVersionUID = 1L;

	public BadRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
