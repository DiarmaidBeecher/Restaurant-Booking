package com.ait.booking.errors;

/**
 * Error Messages
 */
public enum ErrorMessages {
	INVALID_TIME("Time must be between opening hours 16:00 - 23:00"),
	INVALID_VALUE("Number of people must be between 1 and 10");

	private String errorMessage;
	
	ErrorMessages(String errMsg){
		this.errorMessage=errMsg;
	}

	public String getMsg(){
		return errorMessage;
	}
}
