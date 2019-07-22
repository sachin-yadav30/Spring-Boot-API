package org.gn.udip.model;

public class FetcherKafkaDetail {

	String jobId;
	String serviceId;
	String category;
	String blobtype;
	String fetcherId;
	String fetcherCommand;
	String serviceFetcherArgs;
	String scriptName;
	
	public String getJobId() {
		return jobId;
	}
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}
	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBlobtype() {
		return blobtype;
	}
	public void setBlobtype(String blobtype) {
		this.blobtype = blobtype;
	}
	public String getFetcherId() {
		return fetcherId;
	}
	public void setFetcherId(String fetcherId) {
		this.fetcherId = fetcherId;
	}
	public String getFetcherCommand() {
		return fetcherCommand;
	}
	public void setFetcherCommand(String fetcherCommand) {
		this.fetcherCommand = fetcherCommand;
	}
	public String getServiceFetcherArgs() {
		return serviceFetcherArgs;
	}
	public void setServiceFetcherArgs(String serviceFetcherArgs) {
		this.serviceFetcherArgs = serviceFetcherArgs;
	}
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	
	@Override
	public String toString() {
		return "FetcherKafkaModel [jobId=" + jobId + ", serviceId=" + serviceId + ", category=" + category
				+ ", blobtype=" + blobtype + ", fetcherId=" + fetcherId + ", fetcherCommand=" + fetcherCommand
				+ ", serviceFetcherArgs=" + serviceFetcherArgs + ", scriptName=" + scriptName + "]";
	}
}
