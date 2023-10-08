package com.ait.booking.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ait.booking.entity.Booking;

/**
 * Booking Repository
 */
@Repository
public interface BookingRepo extends JpaRepository<Booking, Long> {

}
