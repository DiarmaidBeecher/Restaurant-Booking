package com.ait.drink.exception;

public class DrinkValidationException extends DrinkException {

	private static final long serialVersionUID = 8945672265583L;

	public DrinkValidationException(final String errorMessage) {
		super(errorMessage);
	}
}
