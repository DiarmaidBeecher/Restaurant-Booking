package com.ait.drink.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ait.drink.entity.Drink;
import com.ait.drink.errors.ErrorMessages;
import com.ait.drink.errors.ErrorValidation;
import com.ait.drink.exception.DrinkNotFoundException;
import com.ait.drink.exception.DrinkValidationException;
import com.ait.drink.repo.DrinkRepo;

@RestController
@RequestMapping("/drinks")
public class DrinkController {
	
	Drink drink;
	
	@Autowired
	DrinkRepo drinkRepo;
	
	@Autowired
	ErrorValidation errorValidation;
		
	@GetMapping
	public List<Drink> getAllDrinks() {
		return drinkRepo.findAll();
	}

	@GetMapping("/{id}")
	public Drink getDrinkById(@PathVariable Long id) {
		return drinkRepo.findById(id).get();
	}

	@PostMapping
	public Drink createDrink(@RequestBody Drink drink) throws DrinkValidationException {
		this.drink = drink;
		if(errorValidation.invalidDrink(drink)) {
			throw new DrinkValidationException(ErrorMessages.INVALID_DRINK.getMsg());
		}
		return drinkRepo.save(drink);
	}

	@DeleteMapping("/{id}")
	public void deleteDrink(@PathVariable Long id) throws DrinkNotFoundException {
		try {
			Drink drinkDelete = drinkRepo.findById(id).get();
			drinkRepo.delete(drinkDelete);
		} catch (Exception e) {
			throw new DrinkNotFoundException("Drink Not Found");
		}
	}
}
