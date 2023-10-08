package com.ait.drink.errors;

public enum ErrorMessages {
	INVALID_DRINK("Drink not available to order");

	private String errorMessage;
	
	ErrorMessages(String errMsg){
		this.errorMessage=errMsg;
	}

	public String getMsg(){
		return errorMessage;
	}
}
