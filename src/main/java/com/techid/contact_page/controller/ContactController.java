package com.techid.contact_page.controller;


import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ContactController {
	
	@Autowired
	JavaMailSender mailSender;
	
	@GetMapping("/contact")
	public String showContactForm() {
		return "contact";
	}
	
	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("message");
		
		MimeMessage mailMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
		
		String mailSubject = name + " has sent a message";
		String mailContent = "<p><b>Sender Name:</b> " + name + "</p>";
		mailContent += "<p><b>Sender E-mail:</b> " + email + "</p>";
		mailContent += "<p><b>Subject:</b> " + subject + "</p>";
		mailContent += "<p><b>Content:</b> " + content + "</p>";
		
		helper.setFrom("lennytechiq@gmail.com", "TechAdv Account");
		helper.setTo("leonardrongoma3@gmail.com");
		
		helper.setSubject(mailSubject);
		helper.setText(mailContent, true);
		
		mailSender.send(mailMessage);
		
		return "message";
	}

}
