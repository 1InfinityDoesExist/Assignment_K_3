package com.primary.autowired;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class PrimaryQualifierAutowiredApplication {
	public static void main(String[] args) {

		ApplicationContext applicationContext = SpringApplication.run(PrimaryQualifierAutowiredApplication.class, args);

	}

}
