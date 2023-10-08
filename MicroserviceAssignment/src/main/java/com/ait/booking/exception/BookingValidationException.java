package com.ait.booking.exception;

/**
 * Booking Validation Exception
 */
public class BookingValidationException extends BookingException {

	private static final long serialVersionUID = 8945672265583L;

	public BookingValidationException(final String errorMessage) {
		super(errorMessage);
	}
}
