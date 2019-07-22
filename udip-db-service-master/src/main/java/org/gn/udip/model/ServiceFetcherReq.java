package org.gn.udip.model;

import com.google.gson.JsonElement;

public class ServiceFetcherReq {
	
	private Long serviceFetcherMapperId;
	
	private Long serviceId;
	
	private Long fetcherId;
	
	private JsonElement serviceFetcherArgs;
	
	private String createdBy;

	private String modifiedBy;

	public Long getServiceFetcherMapperId() {
		return serviceFetcherMapperId;
	}

	public void setServiceFetcherMapperId(Long serviceFetcherMapperId) {
		this.serviceFetcherMapperId = serviceFetcherMapperId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getFetcherId() {
		return fetcherId;
	}

	public void setFetcherId(Long fetcherId) {
		this.fetcherId = fetcherId;
	}

	

	public JsonElement getServiceFetcherArgs() {
		return serviceFetcherArgs;
	}

	public void setServiceFetcherArgs(JsonElement serviceFetcherArgs) {
		this.serviceFetcherArgs = serviceFetcherArgs;
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
