package com.example.demo.mail;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.internet.MimeMessage;

@Configuration
public class MailConfig {
	@Bean
	public JavaMailSender javaMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.naver.com");
        mailSender.setPort(465);
        
        mailSender.setUsername("");
        mailSender.setPassword("");
        
        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
       // props.put("mail.starttls.enable", "true");
        props.put("mail.smtp.ssl.enable", "true"); // SSL 사용
        props.put("mail.debug", "true");
        MimeMessage message = mailSender.createMimeMessage();
        
        return mailSender;
	}
}
