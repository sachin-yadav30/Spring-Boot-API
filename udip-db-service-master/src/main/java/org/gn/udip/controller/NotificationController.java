package org.gn.udip.controller;


import org.apache.log4j.Logger;
import org.gn.udip.config.AppProperties;
import org.gn.udip.model.Email;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class NotificationController {
	
	@Autowired
	private EmailService emailService;
	
	private static Logger logger=Logger.getLogger(NotificationController.class);

	@PostMapping("/sendmail")
	public RequestStatus sendMail(@RequestBody Email mail) throws Exception {
		
		logger.info("REQUEST ARRIVED : EMAIL SERVICE INITIATED");
		
		mail.setFrom(AppProperties.MAILER_ID);
		mail.setSubject(AppProperties.MAILER_SUBJECT);
		
		emailService.sendMail(mail);
		logger.info("Email sent successfully to : "+mail.getTo());
		logger.info("EMAIL SERVICE COMPLETED");
	
		return new RequestStatus("00","Request Successfuly Processed");
		
	}
}
