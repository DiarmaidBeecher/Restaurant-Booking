package com.ait.booking.exception;

/**
 * Booking Not Found Exception
 */
public class BookingNotFoundException extends BookingException {

	private static final long serialVersionUID = 8352231428732L;

	public BookingNotFoundException(final String errorMessage) {
		super(errorMessage);
	}

}
