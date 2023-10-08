package com.ait.drink.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ait.drink.entity.Drink;

@Repository
public interface DrinkRepo extends JpaRepository<Drink, Long> {

}
