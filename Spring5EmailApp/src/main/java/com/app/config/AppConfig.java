package com.app.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

//this is our main configuration class for mailing 
@Configuration
@ComponentScan(basePackages = { "com.app" })
public class AppConfig {

	/*
	 * this is JavaMailSenderImpl bean , here we set 5 properties of this class host
	 * port username password javaMailProperties
	 */
	@Bean
	public JavaMailSenderImpl mail() {
		JavaMailSenderImpl mail = new JavaMailSenderImpl();

		mail.setHost("smtp.gmail.com");
		mail.setPort(587);
		mail.setUsername("**********@gmail.com");
		mail.setPassword("******");
		mail.setJavaMailProperties(props());

		return mail;
	}

	/*
	 * here we have set the properties of mail travel protocol
	 */
	private Properties props() {
		Properties p = new Properties();
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.starttls.enable", "true");

		return p;
	}

}
