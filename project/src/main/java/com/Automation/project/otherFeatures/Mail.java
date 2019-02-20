package com.Automation.project.otherFeatures;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.FileDataSource;

public class Mail
{
	public static Calendar calendar;
	public static SimpleDateFormat format;
	
	public static void Mail1(String file1) throws AddressException, MessagingException
	{
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		//Socket factory
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		//SMTP server port
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props,
				 
				new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
 					return new PasswordAuthentication("specdeck3116@gmail.com", "kohler@123");
			}
		});
		
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("specdeck3116@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("specdeck3116@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("specdeck3116@gmail.com"));
			message.setSubject("Execution Report");
			BodyPart messageBodyPart1 = new MimeBodyPart();
			messageBodyPart1.setText("Please find the report in the Attachment section");
			MimeBodyPart messageBodyPart2 = new MimeBodyPart();
			String filename = file1; 
			DataSource source = new FileDataSource(filename);
			messageBodyPart2.setDataHandler(new DataHandler(source));
			messageBodyPart2.setFileName(filename);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart2);
			multipart.addBodyPart(messageBodyPart1);
			message.setContent(multipart);
			Transport.send(message);
			System.out.println("=====Email Sent=====");
		} 
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
 
	}

}
