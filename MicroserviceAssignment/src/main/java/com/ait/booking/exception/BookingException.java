package com.ait.booking.exception;

/**
 * Booking Exception
 */
public abstract class BookingException extends Exception {

	protected BookingException(final String message) {
		super(message);
	}

	private static final long serialVersionUID = 34094350872309L;
}
