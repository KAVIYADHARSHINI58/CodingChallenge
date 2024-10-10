package com.springboot.book_app.exception;

public class InvalidInputException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message;
	private int statusCode=300;
	public InvalidInputException(String message) {
		super();
		this.message = message;
	}
	public String getMessage() {
		return message;
	}
	public int getStatusCode() {
		return statusCode;
	}
	
	

}
