package com.vessel.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.vessel.api.model")
public class Boot {
	/**
	 * Main start project
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(Boot.class, args);
	}
}
