package com.ait.booking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ait.booking.controllers.BookingController;
import com.ait.booking.entity.Booking;
import com.ait.booking.errors.ErrorMessage;
import com.ait.booking.errors.ErrorMessages;
import com.ait.booking.exception.BookingValidationException;
import com.ait.booking.service.BookingService;

@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {
	
	@Autowired
	BookingController bookingController;
	
	@MockBean
	BookingService bookingService;
	
	@Test
	void testGetAllBookings() {
		Booking bookingSaved = buildBooking();
		bookingSaved.setId(1L);
		ArrayList<Booking> bookings = new ArrayList<>();
		bookings.add(bookingSaved);
		when(bookingService.getAllBookings()).thenReturn(bookings);
		ResponseEntity response = bookingController.getAllBookings();
		assertEquals(response.getStatusCode(),HttpStatus.OK);
		
		ArrayList<Booking> bookingsFound = (ArrayList<Booking>) bookingService.getAllBookings();
		Booking bookingFound = bookingsFound.get(0);
		assertEquals(bookingFound.getNumPeople(),4);
		assertEquals(bookingFound.getArrivalTime(),1800);
		assertEquals(bookingFound.getDuration(),60);
	}
	
	@Test
	void testGetAllBookingsEmpty() {
		ArrayList<Booking> bookings = new ArrayList<>();
		when(bookingService.getAllBookings()).thenReturn(bookings);
		ResponseEntity response = bookingController.getAllBookings();
		assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	
	@Test
	void testAddBookingSuccess() throws BookingValidationException {
		Booking booking = buildBooking();
		Booking bookingSaved = buildBooking();
		bookingSaved.setId(1L);
		when(bookingService.createBooking(any())).thenReturn(bookingSaved);
		ResponseEntity response = bookingController.addBooking(booking);
		assertEquals(response.getStatusCode(),HttpStatus.CREATED);
		
		Booking bookingReturn = bookingService.createBooking(booking);
		assertEquals(bookingReturn.getNumPeople(),4);
		assertEquals(bookingReturn.getArrivalTime(),1800);
		assertEquals(bookingReturn.getDuration(),60);
	}
	
	@Test
	void testAddBookingFail() throws BookingValidationException {
		Booking booking = buildBooking();
		when(bookingService.createBooking(any())).thenThrow(new BookingValidationException(ErrorMessages.INVALID_VALUE.getMsg()));
		ResponseEntity response = bookingController.addBooking(booking);
		assertEquals(response.getStatusCode(),HttpStatus.BAD_REQUEST);
		
		ErrorMessage errorMsg = (ErrorMessage) response.getBody();
		assertEquals(ErrorMessages.INVALID_VALUE.getMsg(),errorMsg.getErrorMessage());
	}

	@Test
	void testAddBookingFailNumPeople() throws BookingValidationException {
		Booking booking = buildBooking();
		booking.setNumPeople(11);	
		try {
			bookingController.addBooking(booking);
		} catch (Exception e) {
			assertEquals(ErrorMessages.INVALID_VALUE.getMsg(),e);
		}
	}
	
	@Test
	void testAddBookingFailArrivalTime() throws BookingValidationException {
		Booking booking = buildBooking();
		booking.setArrivalTime(2300);	
		try {
			bookingController.addBooking(booking);
		} catch (Exception e) {
			assertEquals(ErrorMessages.INVALID_TIME.getMsg(),e);
		}
	}
	
	/**
	@Test
	void testGetBookingById() {
		Booking bookingSaved = buildBooking();
		bookingSaved.setId(1L);
		Optional<Booking> bookings = Optional.of(bookingSaved);
		when(bookingService.getBooking(any())).thenReturn(bookings);
		ResponseEntity response = bookingController.getBooking(bookings.get().getId());
		assertEquals(response.getStatusCode(),HttpStatus.OK);
	
		Optional<Booking> carFound = bookingService.getBooking(bookings.get().getId());
		if (carFound.isPresent()) {
			assertEquals(carFound.get().getNumPeople(),4);
			assertEquals(carFound.get().getArrivalTime(),1800);
			assertEquals(carFound.get().getDuration(),60);
		}
	}
	
	@Test
	void testGetBookingByIdEmpty() {
		Optional<Booking> bookings = Optional.empty();
		when(bookingService.getBooking(any())).thenReturn(bookings);
		ResponseEntity response = bookingController.getBooking(1L);
		assertEquals(response.getStatusCode(),HttpStatus.NO_CONTENT);
	}
	*/
	
	Booking buildBooking() {
		Booking buildBooking = new Booking();
		buildBooking.setNumPeople(4);
		buildBooking.setArrivalTime(1800);
		return buildBooking;
	}
}
