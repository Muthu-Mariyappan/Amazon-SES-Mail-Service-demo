package com.gmm.service.mail;

//Muthu Mariyappan G

import java.util.List;

public interface MailService {
	
	public String sendMail();
	public void setToAddress(List<String> toAddress);
}
