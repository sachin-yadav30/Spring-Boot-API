package org.gn.udip.model;

import com.google.gson.JsonArray;

public class ServiceMasterHistoryReq {
	
	private Long serviceId;

	private String type;

	private JsonArray change;
	
	private String createdBy;

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public JsonArray getChange() {
		return change;
	}

	public void setChange(JsonArray change) {
		this.change = change;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	

}
