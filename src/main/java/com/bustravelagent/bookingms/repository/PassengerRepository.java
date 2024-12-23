package com.bustravelagent.bookingms.repository;

import com.bustravelagent.bookingms.model.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>  {
}
