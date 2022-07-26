package com.app.util;

import javax.mail.internet.MimeMessage;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.app.config.AppConfig;

@Component
public class AppMailSender {
	
   private JavaMailSender mailSender;
   
   /*
    * to : to whom we wanna send the mail
    * sub : subject 
    * text : inner text 
    * FileSystemResource : attachment
    */
   public boolean sendEmail(String to ,String sub ,String text,FileSystemResource file) {
	   
	   // this status will specify that whether mail has been sent successfully or not 
	   boolean status=false;
//	   create an object of application context 
	   ApplicationContext applicationContext=new AnnotationConfigApplicationContext(AppConfig.class);
//	    get an object of JavaMailSender from application context
	   mailSender=applicationContext.getBean("mail",JavaMailSender.class);
	   
	   
	   try {
		   
//		   1. Create a message object 
		   MimeMessage message =mailSender.createMimeMessage();
		   
//         2. Create a helper class object  , this will help us in sending the mime message 
		   MimeMessageHelper helper=new MimeMessageHelper(message, file!=null?true:false);
		   
//		   3. Compose Message 
		   helper.setTo(to);
		   helper.setFrom("********@gmail.com");
		   helper.setSubject(sub);
           helper.setText(text);
           helper.addAttachment(file.getFilename(), file);
           
//           4. Send email  with the help of javamail sender 
           mailSender.send(message);
           status =true;     
	   }
	   // handle the  runtime exception 
	   catch(Exception e ) {
		   status=false;
		   e.printStackTrace();
		   System.out.println(e);
	   }
	   
	   
	   return status;
	   
   }

}
