package com.gmm.service.mail;

//Muthu Mariyappan G

import java.util.ArrayList;
import java.util.List;



import org.springframework.stereotype.Service;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest; 


@Service
public class HogwartsMailService implements MailService{
	  
	  private static final String FROM = "minerva@hogwarts.edu"; // your from address, must be verified with Amazon SES
	  private List<String> toAddress;
	  private static final String SUBJECT = "Admission in HOGWARTS SCHOOL of WITCHCRAFT and WIZARDRY";
	  
	  // The HTML body for the email.
	  private static final String HTMLBODY = "<h3>Headmaster: ALBUS DUMBLEDORE</h3>"+
	  "(Order of Merlin, First Class, Grand Sorc., Chf. Warlock,Supreme Mugwump, International Confed. of Wizards)<br/>"+
	  "Dear Wizard/Witch,<br/>We are pleased to inform you that you have been accepted at Hogwarts School of Witchcraft and Wizardry. "+ 
	  "<br/>Please find enclosed a list of all necessary books and equipment.<br/>"+
	  "Term begins on September 1. We await your owl by no later than July 31."+
	  "Yours sincerely,<br/><br/>Minerva McGonagall,<br/>Deputy Headmistress";

	  // The email body for recipients with non-HTML email clients.
	  private static final String TEXTBODY = "Headmaster: ALBUS DUMBLEDORE\n"+
			  "(Order of Merlin, First Class, Grand Sorc., Chf. Warlock,Supreme Mugwump, International Confed. of Wizards)"+
			  "\nDear wizard/witch,\nWe are pleased to inform you that you have been accepted at Hogwarts School of Witchcraft and Wizardry. "+ 
			  "\nPlease find enclosed a list of all necessary books and equipment."+
			  "\nTerm begins on September 1. We await your owl by no later than July 31."+
			  "\nYours sincerely,\n\nMinerva McGonagall,\nDeputy Headmistress";
	  
	  
	  public HogwartsMailService() {
		  this.toAddress = new ArrayList<>();
		  this.toAddress.add("harrypotter@gmail.com");
	  }
	  
	  @Override
	  public void setToAddress(List<String> toAddress) {
		  this.toAddress = new ArrayList<>(toAddress);
	  }
	  
	  @Override
	  public String sendMail() {
		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withRegion(Regions.DEFAULT_REGION).build(); // builder pattern to build email service
			SendEmailRequest request = new SendEmailRequest()
					.withDestination(new Destination().withToAddresses(toAddress)) //setting to addresses
					.withMessage(new Message() // setting body and subject
							.withBody(new Body()
									.withHtml(new Content()
											.withCharset("UTF-8").withData(HTMLBODY))
									.withText(new Content()
											.withCharset("UTF-8").withData(TEXTBODY)))
							.withSubject(new Content()
									.withCharset("UTF-8").withData(SUBJECT)))
					.withSource(FROM); //setting to address
			client.sendEmail(request); // sending mail
			return ("Email sent!");
	    } 
		catch (Exception ex) {
			return "The email was not sent. Error message: "; 
	    }
	}
}