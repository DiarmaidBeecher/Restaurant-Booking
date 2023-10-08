package com.ait.booking.feignClient;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import com.ait.booking.entity.Drink;

import org.springframework.web.bind.annotation.*;

@FeignClient("zuul-api-gateway")
public interface DrinkFeignClient {
	
	@GetMapping("/drink-service/drinks")
	List<Drink> getAllDrinks();
	
	@GetMapping("/drink-service/drinks/{id}")
	Drink getDrinkById(@PathVariable Long id);
	
	@PostMapping("/drink-service/drinks")
	Drink createDrink(@RequestBody Drink drink);

	@DeleteMapping("/drink-service/drinks/{id}")
	void deleteDrink(@PathVariable Long id);
	
}