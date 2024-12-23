package com.bustravelagent.bookingms.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

@Entity
@Table(name = "transaction")
public class BusInventory {
    @Id
    @Column(name = "bus_no", nullable = false)
    private Integer busNo;

    @Column(name = "available_seats", nullable = false)
    private Integer availableSeats;

    @Column(name = "last_updated_date")
    private Instant lastUpdatedDate;

    public Integer getBusNo() {
        return busNo;
    }

    public void setBusNo(Integer busNo) {
        this.busNo = busNo;
    }

    public Integer getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(Integer availableSeats) {
        this.availableSeats = availableSeats;
    }

    public Instant getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Instant lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    @Override
    public String toString() {
        return "BusInventory{" +
                "busNo=" + busNo +
                ", availableSeats=" + availableSeats +
                ", lastUpdatedDate=" + lastUpdatedDate +
                '}';
    }
}
