package com.care.am.common;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {
	@Bean
	public static JavaMailSender mailSender() {
		JavaMailSenderImpl jms = new JavaMailSenderImpl();
		jms.setHost("smtp.gmail.com"); //google smtp 
		jms.setPort(587);
		jms.setUsername("mn.animedi@gmail.com"); // 보내는계정 
		jms.setPassword("smkjpxtagbobvxzc");
		
		Properties prop = new Properties();                 // 아래는 모두 기본설정
        prop.setProperty("mail.transport.protocol", "smtp");       //메일전송 프로토콜 = smtp 
        prop.setProperty("mail.smtp.auth", "true");              //메일을 보낼 때 사용자 인증을 할거냐 
        prop.setProperty("mail.smtp.starttls.enable", "true");         // ttl이라는 암호화 방식을 설정 할거냐 
        prop.setProperty("mail.debug", "true");             // 디버그 - 로그찍어주는거
        jms.setJavaMailProperties(prop); 

        return jms;

	}
}