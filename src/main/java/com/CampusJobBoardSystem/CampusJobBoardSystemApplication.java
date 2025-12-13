package com.CampusJobBoardSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

/**
 * Main application class for Campus Job Board System.
 * A web application designed to connect students seeking jobs with employers
 * posting job opportunities on campus.
 *
 * @author Campus Job Board System Team
 * @version 1.0
 */
@SpringBootApplication
@ConfigurationPropertiesScan
public class CampusJobBoardSystemApplication {

	/**
	 * Main entry point for the Spring Boot application.
	 *
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(CampusJobBoardSystemApplication.class, args);
	}

}
