package com.public_library;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class PublicLibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(PublicLibraryApplication.class, args);
	}

}
