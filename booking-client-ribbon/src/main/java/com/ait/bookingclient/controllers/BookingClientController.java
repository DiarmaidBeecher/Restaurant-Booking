package com.ait.bookingclient.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ait.bookingclient.entity.Booking;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.cloud.client.ServiceInstance;

import java.io.DataInput;
import java.util.List;

@RestController
public class BookingClientController {

	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("/booking-client-ribbon/{bookingId}")
	public Booking getBooking(@PathVariable long bookingId) {
		ResponseEntity<Booking> restExchange = restTemplate.exchange("http://booking-service/bookings/{bookingId}", HttpMethod.GET,
				null, Booking.class, bookingId);
		return restExchange.getBody();
	}

}
