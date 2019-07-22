package org.gn.udip.model;

public class ServiceParserDetailResponsePojo {
	
	private Long mapperId;
	private Long serviceId;
	private ParserDetailResponsePojo parserDetail;
	private String parserArgs;
	private Boolean isTestJob;
	private Boolean isConcatenatedData;
	
	public Long getMapperId() {
		return mapperId;
	}
	public void setMapperId(Long mapperId) {
		this.mapperId = mapperId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public ParserDetailResponsePojo getParserDetail() {
		return parserDetail;
	}
	public void setParserDetail(ParserDetailResponsePojo parserDetail) {
		this.parserDetail = parserDetail;
	}
	public String getParserArgs() {
		return parserArgs;
	}
	public void setParserArgs(String parserArgs) {
		this.parserArgs = parserArgs;
	}
	public Boolean getIsTestJob() {
		return isTestJob;
	}
	public void setIsTestJob(Boolean isTestJob) {
		this.isTestJob = isTestJob;
	}
	public Boolean getIsConcatenatedData() {
		return isConcatenatedData;
	}
	public void setIsConcatenatedData(Boolean isConcatenatedData) {
		this.isConcatenatedData = isConcatenatedData;
	}

}
