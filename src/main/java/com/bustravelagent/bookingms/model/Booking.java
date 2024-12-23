package com.bustravelagent.bookingms.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "booking")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_no", nullable = false)
    private Integer bookingNo;

    @Column(name = "bus_no", nullable = false)
    private Integer busNo;

    @Column(name = "booking_date")
    private Instant bookingDate;

    @Column(name = "source", length = 50)
    private String source;

    @Column(name = "destination", length = 50)
    private String destination;

    @Column(name = "no_of_seats", nullable = false)
    private Integer noOfSeats;

    @Column(name = "status", length = 50)
    private String status;

    public Integer getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(Integer bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Integer getBusNo() {
        return busNo;
    }

    public void setBusNo(Integer busNo) {
        this.busNo = busNo;
    }

    public Instant getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Instant bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(Integer noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingNo=" + bookingNo +
                ", busNo=" + busNo +
                ", bookingDate=" + bookingDate +
                ", source='" + source + '\'' +
                ", destination='" + destination + '\'' +
                ", noOfSeats=" + noOfSeats +
                ", status='" + status + '\'' +
                '}';
    }
}
