package org.gn.udip.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.gn.udip.config.AppProperties;
import org.gn.udip.exception.MailerException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static Logger logger = Logger.getLogger(EmailService.class);

	private static Map<String, String> templates = null;

	@Autowired
	private JavaMailSender emailSender;

	EmailService() {
		templates = new HashMap<String, String>();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("email-templates").getFile());
			for (File f : file.listFiles()) {
				if (f.isFile()) 
					templates.put(f.getName(), new String(Files.readAllBytes(f.toPath())));
			}
		} catch (IOException e) {
			logger.error("FAILED to load Email templates");
			logger.error(e);
		}

	}

	private void sendHtmlMail(Email mail) throws MessagingException {
	
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		List<String> emailIDs = new ArrayList<>();
		helper.setSubject(mail.getSubject());
		helper.setText(mail.getContent(),true);
		for(String emailId : mail.getTo().split(","))
			emailIDs.add(emailId.replace(",", ""));
		
		helper.setTo(emailIDs.toArray(new String[emailIDs.size()]));
		helper.setFrom(mail.getFrom());

		for (File file : mail.getAttachments()) {
			helper.addAttachment(file.getName(), file);
		}
		
		emailSender.send(message);
		logger.info("Email Sent using HTML template");

	}

	private void sendSimpleMessage(Email mail) {

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(mail.getTo());
		mailMessage.setFrom(mail.getFrom());
		mailMessage.setSubject(mail.getSubject());
		mailMessage.setText(mail.getContent());
		
		emailSender.send(mailMessage);
		logger.info("Email Sent with attachment using TXT template");
	}

	public void sendMail(Email mail) throws MailerException, ValidationException {
		String str = null;
		try {
			validateEmailRequest(mail);
			str = templates.get(AppProperties.MAILER_TEMPLATE);
			str = str.replace("{{user}}", mail.getUser()).replace("{{date}}", mail.getDate()).replace("{{content}}", mail.getContent());
			mail.setContent(str);
			sendHtmlMail(mail);

		} catch (ValidationException e) {
			logger.error("Email Service failed due to validation exception: "+e.getMessage());
			logger.error("EMAIL SERVICE FAILED");
			throw new ValidationException("422","Validation Failure - Email Service failed");
		}
		catch (Exception e) {
			logger.error("Email Service failed due to exception: "+e.getMessage());
			logger.error("EMAIL SERVICE FAILED");
			throw new MailerException("400","Bad Request - Email Service failed");
		}

	}
	
	private void validateEmailRequest(Email mail) throws ValidationException {

		if(!nullAndEmptyCheck(mail.getTo())){
			throw new ValidationException("Email To detail not provided in request.");
		}

		if(!nullAndEmptyCheck(mail.getContent())){
			throw new ValidationException("Email Content details not provided in request.");
		}
	}

	private boolean nullAndEmptyCheck(String data) {

		boolean isDataPresent=false;
		if(data != null && !data.trim().isEmpty()){
			isDataPresent=true;
		}

		return isDataPresent;
	}

}
