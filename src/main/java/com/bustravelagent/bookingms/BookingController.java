package com.bustravelagent.bookingms;

import com.bustravelagent.bookingms.model.*;
import com.bustravelagent.bookingms.repository.BookingRepository;
import com.bustravelagent.bookingms.repository.PassengerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;

@RestController
@RequestMapping("/api/v1")
public class BookingController {

    private static final Logger log = LoggerFactory.getLogger(BookingController.class);


    private RestTemplate restTemplate;

    private BookingRepository bookingRepository;

    private PassengerRepository passengerRepository;

    @Value("${gateway.host}")
    private String gateway_host;

    @Value("${gateway.port}")
    private String gateway_port;

    public BookingController(RestTemplate restTemplate, BookingRepository bookingRepository, PassengerRepository passengerRepository) {
        this.restTemplate = restTemplate;
        this.bookingRepository = bookingRepository;
        this.passengerRepository = passengerRepository;
    }

    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestParam("busNo") Integer busNo,
                                                 @RequestParam("seats") Integer requiredSeats) {

        ResponseEntity<BusInventory> inventoryResponse = restTemplate.exchange(
                "http://"+gateway_host+":"+gateway_port+"/businventory/api/v1/get/inventory/"+ busNo,
                HttpMethod.GET,
                null,
                BusInventory.class);
        Booking booking = new Booking();
        if ((inventoryResponse.getBody().getAvailableSeats() >= requiredSeats)
             && (inventoryResponse.getStatusCode() == HttpStatus.OK)) {
            // 2. Create booking with status "PENDING"
            ResponseEntity<BusRoute> busRouteResponse = restTemplate.exchange(
                    "http://"+gateway_host+":"+gateway_port+"/master-data/api/v1/get/bus/"+ busNo,
                    HttpMethod.GET,
                    null,
                    BusRoute.class);
            if(busRouteResponse.getStatusCode() == HttpStatus.OK) {
                booking.setBusNo(busRouteResponse.getBody().getBusNo());
                booking.setBookingDate(Instant.now());
                booking.setSource(busRouteResponse.getBody().getSource());
                booking.setDestination(busRouteResponse.getBody().getDestination());
                booking.setNoOfSeats(requiredSeats);
                booking.setStatus("PENDING");
                bookingRepository.save(booking);

                ResponseEntity<Payment> paymentResponse = restTemplate.exchange(
                        "http://"+gateway_host+":"+gateway_port+"/payments/api/v1/add/payment/"+ busNo,
                        HttpMethod.POST,
                        null,
                        Payment.class);

                Passenger passenger = new Passenger();
                passenger.setBookingNo(busNo);
                passengerRepository.save(passenger);

                bookingRepository.updateBookingDetails(busNo,"Confirmed");

            }
            else {
                return new ResponseEntity<>(booking, HttpStatus.NOT_IMPLEMENTED );
            }
        } else {
            log.info("Not enough seats available.");
            return new ResponseEntity<>(booking, HttpStatus.NOT_FOUND );
        }
        return new ResponseEntity<>(booking, HttpStatus.CREATED);
    }

}
