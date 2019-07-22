package org.gn.udip.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Email {
	
	private String from;
	
	private String to;
	
	private String user = "UDIS Subscriber";
	
	//for specfic format use new SimpleDateFormat("dd-MM-yyyy hh.mm.ss").format(Calendar.getInstance().getTime());
	private String date = Calendar.getInstance().getTime().toString(); 
	
	private String subject;
	
	private String content;
	
	private List<File> attachments = new ArrayList<>();

	public Email() {
	}

	public Email(String from, String to, String subject, String content) {
		this.setFrom(from);
		this.setTo(to);
		this.setSubject(subject);
		this.setContent(content);
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public List<File> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<File> attachments) {
		this.attachments = attachments;
	}

}
