package com.ait.drink.exception;

public abstract class DrinkException extends Exception {

	protected DrinkException(final String message) {
		super(message);
	}

	private static final long serialVersionUID = 34094350872309L;
}
