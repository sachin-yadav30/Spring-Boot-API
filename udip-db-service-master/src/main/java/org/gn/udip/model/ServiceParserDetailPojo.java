package org.gn.udip.model;

import java.util.Date;

import com.google.gson.JsonElement;

public class ServiceParserDetailPojo {
	
	private Long id;
	private Long serviceId;
	private Long parserId;
	private JsonElement serviceParserArgs;
	private String createdBy;
	private String modifiedBy;
	private Date createdOn;
	private Date modifiedOn;
	
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
	public Long getParserId() {
		return parserId;
	}
	public void setParserId(Long parserId) {
		this.parserId = parserId;
	}
	public JsonElement getServiceParserArgs() {
		return serviceParserArgs;
	}
	public void setServiceParserArgs(JsonElement serviceParserArgs) {
		this.serviceParserArgs = serviceParserArgs;
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
	public Date getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	public Date getModifiedOn() {
		return modifiedOn;
	}
	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
