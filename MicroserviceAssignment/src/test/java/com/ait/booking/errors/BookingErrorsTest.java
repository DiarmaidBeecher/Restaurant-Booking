package com.ait.booking.errors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ait.booking.entity.Booking;

public class BookingErrorsTest {
	
	ErrorValidation errorValidation;
	Booking booking;
	
	@BeforeEach
	void setUp() {
		errorValidation = new ErrorValidation();
		booking = buildBooking();
	}
	
	@Test
	void testValidTime() {
		assertFalse(errorValidation.invalidTime(booking));
	}
	
	@Test
	void testInvalidTimeLess() {
		booking.setArrivalTime(1559);
		assertTrue(errorValidation.invalidTime(booking));
	}
	
	@Test
	void testInvalidTimeGreater() {
		booking.setArrivalTime(2300);
		assertTrue(errorValidation.invalidTime(booking));
	}
	
	@Test
	void testValidValue() {
		assertFalse(errorValidation.invalidValue(booking));
	}
	
	@Test
	void testInvalidValueLess() {
		booking.setNumPeople(0);
		assertTrue(errorValidation.invalidValue(booking));
	}
	
	@Test
	void testInvalidValueGreater() {
		booking.setNumPeople(11);
		assertTrue(errorValidation.invalidValue(booking));
	}
	
	@Test
	void testDefaultDuration() {
		assertTrue(booking.getDuration()==60);
	}
	
	@Test
	void testGreaterDuration() {
		booking.setNumPeople(5);
		assertTrue(booking.getDuration()==90);
	}
			
	Booking buildBooking() {
		Booking buildBooking = new Booking();
		buildBooking.setNumPeople(4);
		buildBooking.setArrivalTime(1800);
		return buildBooking;
	}

}
