package com.gmm.base;

//Muthu Mariyappan G

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.gmm.service.mail","com.gmm.base.rest"})
public class AmazonSesApplication {

	public static void main(String[] args) {
		SpringApplication.run(AmazonSesApplication.class, args);
	}
}
