package org.gn.udip.model;

public class RequestStatus {
	
	
	public RequestStatus(String statusCode,String statusDesc){
		this.setStatusCode(statusCode);
		this.setStatusDesc(statusDesc);
	}
	
	private String statusCode;
	private String statusDesc;
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
	
	

}
