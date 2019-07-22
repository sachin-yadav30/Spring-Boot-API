package org.gn.udip.model;

import java.util.Date;

public class ServiceMasterPojo {
		
	private Long serviceId;	
	private String serviceName;
	private String uniqueId;
	private String vertical;
	private String category;
	private String owner;
	private String status;
	private String charset;
	private String fetcherRunStatus;
	private String isActive;
	private String isSchedulable;
	private String isConcatenatedData;
	private String createdBy;
	private String modifiedBy;
	private Date createdOn;
	private Date modifiedOn;
	
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getUniqueId() {
		return uniqueId;
	}
	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	public String getVertical() {
		return vertical;
	}
	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCharset() {
		return charset;
	}
	public void setCharset(String charset) {
		this.charset = charset;
	}
	public String getFetcherRunStatus() {
		return fetcherRunStatus;
	}
	public void setFetcherRunStatus(String fetcherRunStatus) {
		this.fetcherRunStatus = fetcherRunStatus;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getIsSchedulable() {
		return isSchedulable;
	}
	public void setIsSchedulable(String isSchedulable) {
		this.isSchedulable = isSchedulable;
	}
	public String getIsConcatenatedData() {
		return isConcatenatedData;
	}
	public void setIsConcatenatedData(String isConcatenatedData) {
		this.isConcatenatedData = isConcatenatedData;
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
