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
		jms.setUsername("mn.animedi@gmail.com"); // �����°��� 
		jms.setPassword("smkjpxtagbobvxzc");
		
		Properties prop = new Properties();                 // �Ʒ��� ��� �⺻����
        prop.setProperty("mail.transport.protocol", "smtp");       //�������� �������� = smtp 
        prop.setProperty("mail.smtp.auth", "true");              //������ ���� �� ����� ������ �Ұų� 
        prop.setProperty("mail.smtp.starttls.enable", "true");         // ttl�̶�� ��ȣȭ ����� ���� �Ұų� 
        prop.setProperty("mail.debug", "true");             // ����� - �α�����ִ°�
        jms.setJavaMailProperties(prop); 

        return jms;

	}
}