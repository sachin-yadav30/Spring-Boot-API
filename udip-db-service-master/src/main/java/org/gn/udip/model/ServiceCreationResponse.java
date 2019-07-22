package org.gn.udip.model;

public class ServiceCreationResponse {

	private String statusCode;
	private String statusDesc;
	private Long serviceId;
	
	public ServiceCreationResponse(String statusCode, String statusDesc,Long serviceId) {
		this.setStatusCode(statusCode);
		this.setStatusDesc(statusDesc);
		this.setServiceId(serviceId);
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusDesc() {
		return statusDesc;
	}

	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}

	public ServiceCreationResponse(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
}
