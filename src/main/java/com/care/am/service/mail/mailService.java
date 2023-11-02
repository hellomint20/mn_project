package com.care.am.service.mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class mailService {
	
	@Autowired JavaMailSender mailSender;
	@Autowired
	//mailContentBuilder mailContentBuilder;
	
	public void tempPwdSendMail(String to,String subject,String body) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body);
			//String content = mailContentBuilder.build(message);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
