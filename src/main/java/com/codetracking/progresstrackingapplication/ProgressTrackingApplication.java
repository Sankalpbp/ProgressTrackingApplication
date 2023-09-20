package com.codetracking.progresstrackingapplication;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ProgressTrackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProgressTrackingApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper ( ) {
		return new ModelMapper ();
	}

}
