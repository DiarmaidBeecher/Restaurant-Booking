package com.ait.booking.errors;

public class ErrorMessage {
	String displayMessage;

	public ErrorMessage(String message) {
		this.displayMessage=message;
	}

	public String getErrorMessage() {
		return displayMessage;
	}

}
