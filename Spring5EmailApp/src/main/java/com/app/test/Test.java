package com.app.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;

import com.app.config.AppConfig;
import com.app.util.AppMailSender;

public class Test {

	public static void main(String[] args) {

//		get bean of AppMailSender
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

		AppMailSender mailSender = (AppMailSender) applicationContext.getBean("appMailSender");

//		set the attachment we want to send along with the email
		FileSystemResource fileSystemResource = new FileSystemResource(
				"/Users/samergosain/Pictures/samer-engagement.jpeg");
		
//		send the email
		boolean flag=mailSender.sendEmail("kolsoro9@gmail.com", "Hello",
				"Welcome To spring email", fileSystemResource);
		
		if(flag) {
			System.out.println("Done");
		}
		else {
			System.out.println("Sorry!!!!");
		}

	}

}
