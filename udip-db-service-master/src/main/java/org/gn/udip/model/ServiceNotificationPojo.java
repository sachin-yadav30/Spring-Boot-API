package org.gn.udip.model;

public class ServiceNotificationPojo {
	
	private Long id;
	private Long serviceId;
	private Long notificationId;
	private String notifyLevel;
	private String receptionList;
	
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
	public Long getNotificationId() {
		return notificationId;
	}
	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}
	public String getNotifyLevel() {
		return notifyLevel;
	}
	public void setNotifyLevel(String notifyLevel) {
		this.notifyLevel = notifyLevel;
	}
	public String getReceptionList() {
		return receptionList;
	}
	public void setReceptionList(String receptionList) {
		this.receptionList = receptionList;
	}

}
