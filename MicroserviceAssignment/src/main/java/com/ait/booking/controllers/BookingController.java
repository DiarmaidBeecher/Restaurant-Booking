package com.ait.booking.controllers;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.booking.entity.Booking;
import com.ait.booking.errors.ErrorMessage;
import com.ait.booking.exception.BookingException;
import com.ait.booking.service.BookingService;

/**
 * Booking Controller
 */
@RestController
@RequestMapping("/bookings")
public class BookingController {

	@Autowired
	BookingService bookingService;

	@GetMapping
	public ResponseEntity getAllBookings() {
		ArrayList<Booking> bookings = (ArrayList<Booking>) bookingService.getAllBookings();
		if (bookings.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(bookings);
		} else {
			return (ResponseEntity) ResponseEntity.status(HttpStatus.OK).body(bookings);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity getBooking(@PathVariable("id") Long id) {
		Optional<Booking> booking = Optional.ofNullable(bookingService.getBooking(id));
		if (booking.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(booking);
		} else {
			return ResponseEntity.status(HttpStatus.OK).body(booking);
		}
	}

	@PostMapping
	public ResponseEntity addBooking(@RequestBody Booking booking) {
		try {
			Booking savedBooking = bookingService.createBooking(booking);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedBooking);
		} catch (BookingException e) {
			ErrorMessage errorMessage = new ErrorMessage(e.getMessage());
			return ResponseEntity.badRequest().body(errorMessage);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteBooking(@PathVariable("id") Long id) {
		try {
			bookingService.deleteBooking(id);
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

}
