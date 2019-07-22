package org.gn.udip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_notification_detail")
public class UserNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "notification_id")
	private Long notificationId;
	
	@Column(name = "reception_list")
	private String receptionList;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public String getReceptionList() {
		return receptionList;
	}

	public void setReceptionList(String receptionList) {
		this.receptionList = receptionList;
	}

	
	
	
	
}
