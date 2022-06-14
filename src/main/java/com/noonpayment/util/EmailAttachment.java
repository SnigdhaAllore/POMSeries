package com.noonpayment.util;

import com.noonpayment.base.TestBase;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.Properties;

public final class EmailAttachment {
	private EmailAttachment(){}
	public static void sendEmail() {
		try {
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(Constants.USER_NAME, TestBase.environment.getEPwd());
			}
		});
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Constants.FROM));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(Constants.TO));
			message.setSubject("***Automation Report***");
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Hi Team,\n Please find the Automation Report from the attachment.\n\nThanks!");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setDataHandler(new DataHandler( new FileDataSource(Constants.REPORT_LOCATION)));
			messageBodyPart.setFileName(new File(Constants.REPORT_LOCATION).getName());
			multipart.addBodyPart(messageBodyPart);
			message.setContent(multipart);
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	 }
	}