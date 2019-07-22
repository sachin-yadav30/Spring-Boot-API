package org.gn.udip.model;

import com.google.gson.JsonElement;

public class ServiceScheduleMasterReq {
	
	private Long id;
	
	private Long serviceId;
	
	private String frequency;
	
	private JsonElement customFrequency;
	
	private int isActive;
	
	private String timezone;
	
	private String startTime;
	
	private String createdBy;
	
	private String modifiedBy;

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

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public JsonElement getCustomFrequency() {
		return customFrequency;
	}

	public void setCustomFrequency(JsonElement customFrequency) {
		this.customFrequency = customFrequency;
	}

	public int getIsActive() {
		return isActive;
	}

	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	
	
}
