package com.bustravelagent.bookingms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BookingmsApplication {

	public static void main(String[] args) {

		SpringApplication application = new SpringApplication(BookingmsApplication.class);

		// Set the active profile programmatically
		application.setAdditionalProfiles("dev");

		application.run(args);
	}

}
