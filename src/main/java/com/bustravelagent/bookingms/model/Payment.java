package com.bustravelagent.bookingms.model;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "payment")
public class Payment {
    @Id
    @Column(name = "payment_no", nullable = false)
    private Integer paymentNo;

    @Column(name = "booking_no", nullable = false)
    private Integer bookingNo;

    @Column(name = "date_of_payment")
    private Instant dateOfPayment;

    public Integer getPaymentNo() {
        return paymentNo;
    }

    public void setPaymentNo(Integer paymentNo) {
        this.paymentNo = paymentNo;
    }

    public Integer getBookingNo() {
        return bookingNo;
    }

    public void setBookingNo(Integer bookingNo) {
        this.bookingNo = bookingNo;
    }

    public Instant getDateOfPayment() {
        return dateOfPayment;
    }

    public void setDateOfPayment(Instant dateOfPayment) {
        this.dateOfPayment = dateOfPayment;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentNo=" + paymentNo +
                ", bookingNo=" + bookingNo +
                ", dateOfPayment=" + dateOfPayment +
                '}';
    }
}
