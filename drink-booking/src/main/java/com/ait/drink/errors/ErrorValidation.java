package com.ait.drink.errors;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ait.drink.entity.Drink;

@Component
public class ErrorValidation {
	
	List<String> availableDrinks = Arrays.asList("White wine", "Red wine", "Beer", "Cider");
	
	public boolean invalidDrink(Drink drink) {
		if (availableDrinks.contains(drink.getDrink())) {
			return false;
		} else {
			return true;
		}
	}

}
