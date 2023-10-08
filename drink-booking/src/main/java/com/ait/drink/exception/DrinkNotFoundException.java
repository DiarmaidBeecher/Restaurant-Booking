package com.ait.drink.exception;

public class DrinkNotFoundException extends DrinkException {

	private static final long serialVersionUID = 8352231428732L;

	public DrinkNotFoundException(final String errorMessage) {
		super(errorMessage);
	}

}
