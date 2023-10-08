package com.ait.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Main SpringBoot method
 */
@SuppressWarnings("deprecation")
@SpringBootApplication
@EnableFeignClients
public class TableBookingApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TableBookingApplication.class, args);
	}

}
