package com.ait.booking.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Booking Entity
 */
@Entity
@Table(name = "booking")
public class Booking implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int numPeople;

	private int arrivalTime;

	@Column(columnDefinition = "integer default 60")
	private int duration;
	
	@Transient
	private String drink;
	
	public static final int UPPER_TIER_NUM_PEOPLE = 4;
	public static final int DEFAULT_DURATION = 60;
	public static final int UPPER_TIER_TIME = 90;

	public Booking () {
	}
	
	public Booking (Long id, int numPeople, int arrivalTime, int duration) {
		this.id = id;
		this.numPeople = numPeople;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
	}
	
	public Booking (int numPeople, int arrivalTime, int duration) {
		this.numPeople = numPeople;
		this.arrivalTime = arrivalTime;
		this.duration = duration;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumPeople() {
		return numPeople;
	}
	public void setNumPeople(int numPeople) {
		this.numPeople = numPeople;
	}
	public int getArrivalTime() {
		return arrivalTime;
	}
	public void setArrivalTime(int arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	public int getDuration() {
		if (numPeople > UPPER_TIER_NUM_PEOPLE) {
			this.duration = UPPER_TIER_TIME;
		} else {
			this.duration = DEFAULT_DURATION;
		}
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	public String getDrink() {
		return drink;
	}
	public void setDrink(String drink) {
		this.drink = drink;
	}
}