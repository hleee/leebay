package com.codepresso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LeebayApplication {

//	public static void main(String[] args) {
//		SpringApplication app = new SpringApplication(LeebayApplication.class);
//		app.setWebApplicationType(WebApplicationType.NONE);
//		app.run(args);
//	}
	
	public static void main(String[] args) {
		SpringApplication.run(LeebayApplication.class, args);
	}

}
