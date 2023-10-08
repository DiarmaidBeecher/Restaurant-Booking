package com.ait.booking.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.booking.entity.Booking;
import com.ait.booking.entity.Drink;
import com.ait.booking.errors.ErrorMessages;
import com.ait.booking.errors.ErrorValidation;
import com.ait.booking.exception.BookingNotFoundException;
import com.ait.booking.exception.BookingValidationException;
import com.ait.booking.feignClient.DrinkFeignClient;
import com.ait.booking.repo.BookingRepo;
/**
 * Booking Service
 */
@Service
public class BookingService {
	
	Booking booking;
	
	@Autowired
	BookingRepo bookingRepo;
	
	@Autowired
	ErrorValidation errorValidation;
	
	@Autowired
	DrinkFeignClient drinkFeignClient;

	public List<Booking> getAllBookings() {

		List<Drink> allDrinks = drinkFeignClient.getAllDrinks();
		Map<Long, String> drinkMap = allDrinks.stream().collect(Collectors.toMap(Drink::getId, Drink::getDrink));
		
		List<Booking> allBookings = bookingRepo.findAll();
		allBookings.stream().filter(b -> drinkMap.containsKey(b.getId())).forEach(b -> {
			b.setDrink(drinkMap.get(b.getId()));
		});
		
		return allBookings;
	}
	
	public Booking getBooking(Long id) {
		
		Drink drink = drinkFeignClient.getDrinkById(id);
		Booking booking = bookingRepo.findById(id).get();
		
		booking.setDrink(drink.getDrink());
				
		return booking;
	}
	
	public Booking createBooking(Booking book) throws BookingValidationException {
		this.booking = book;
		if (errorValidation.invalidValue(book)) {
			throw new BookingValidationException(ErrorMessages.INVALID_VALUE.getMsg());
		}
		if (errorValidation.invalidTime(book)) {
			throw new BookingValidationException(ErrorMessages.INVALID_TIME.getMsg());
		}
		Drink drink = new Drink(booking.getDrink());
		drinkFeignClient.createDrink(drink);
		
		return bookingRepo.save(booking);
	}
	
	public void deleteBooking(Long id) throws BookingNotFoundException {
		try {
			Booking bookingDelete = bookingRepo.findById(id).get();
			
			drinkFeignClient.deleteDrink(id);
			bookingRepo.delete(bookingDelete);
			
		} catch (Exception e) {
			throw new BookingNotFoundException("Booking Not Found");
		}
	}
}
