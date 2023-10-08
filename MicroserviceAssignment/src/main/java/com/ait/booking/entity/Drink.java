package com.ait.booking.entity;

public class Drink {

	private Long id;
	private String drink;

	public Drink () {
	}
	
	public Drink (String drink) {
		this.drink = drink;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
}