package com.tka.crickotrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrickoTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrickoTrackApplication.class, args);
		System.err.println("Im in crickotrack");
	}

}
