package com.gmm.base.rest;

import java.util.Arrays;

//Muthu Mariyappan G

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gmm.service.mail.MailService;

@RestController
public class AmazonSesController {

	@Autowired
	MailService mailService;
	
	@RequestMapping(value="/hogwarts")
	public String welcome() {
		return "<h2> Welcome to Hogwarts!!!</h2>";
	}
	
	@RequestMapping(value="/hogwarts/sendmail")
	public String sendMail(@RequestParam("to") String[] toAddresses) {
		mailService.setToAddress(Arrays.asList(toAddresses));
		return mailService.sendMail();
	}
	
	
}