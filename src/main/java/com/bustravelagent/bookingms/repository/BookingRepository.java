package com.bustravelagent.bookingms.repository;

import com.bustravelagent.bookingms.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BookingRepository extends JpaRepository<Booking, Integer>  {

    @Modifying
    @Transactional
    @Query("UPDATE Booking b SET b.status = :status WHERE b.busNo = :busNo")
    int updateBookingDetails(Integer busNo, String status);
}
