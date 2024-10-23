package com.studentCatering;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentCateringServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentCateringServicesApplication.class, args);
	}
	public static final String corsAllowedUrl = "http://localhost:3000";
}
