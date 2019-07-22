package org.gn.udip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "service_notification_detail")
public class ServiceNotification {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "service_id")
	private Long serviceId;
	
	@OneToOne
	@JoinColumn(name="notification_id")
	private UserNotification notifyConf;
	
	@Column(name = "notification_level")
	private String notifyLevel;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public UserNotification getNotifyConf() {
		return notifyConf;
	}

	public void setNotifyConf(UserNotification notifyConf) {
		this.notifyConf = notifyConf;
	}

	public NotificationLevelEnum getNotifyLevel() {
		return NotificationLevelEnum.valueOf(notifyLevel);
	}

	public void setNotifyLevel(NotificationLevelEnum notifyLevelEnum) {
		this.notifyLevel = notifyLevelEnum.toString();
	}

	
}
