package com.clayfin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.clayfin")
public class PetMartApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetMartApplication.class, args);
	}

}
