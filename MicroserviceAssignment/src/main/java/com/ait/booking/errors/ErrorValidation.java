package com.ait.booking.errors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ait.booking.entity.Booking;
import com.ait.booking.repo.BookingRepo;

/**
 * Error Validation
 */
@Component
public class ErrorValidation {

	@Autowired
	BookingRepo bookingRepo;

	public boolean invalidTime(Booking booking) {
		return (booking.getArrivalTime()<1600||booking.getArrivalTime()>=2300);
	}

	public boolean invalidValue(Booking booking) {
		return (booking.getNumPeople()<=0||booking.getNumPeople()>=11);
	}

}
