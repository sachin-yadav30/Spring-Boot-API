package org.gn.udip.model;

import com.google.gson.JsonArray;

public class FetcherMasterReq {

	private Long fetcherId;
	
	private String fetcherName;

	private String scriptName;

	private String fetcherCommand;

	private JsonArray mandatoryArgs;

	private JsonArray optionalArgs;

	private int fetcherType;

	private String isActive;

	private String createdBy;

	private String modifiedBy;

	public Long getFetcherId() {
		return fetcherId;
	}

	public void setFetcherId(Long fetcherId) {
		this.fetcherId = fetcherId;
	}

	public String getFetcherName() {
		return fetcherName;
	}

	public void setFetcherName(String fetcherName) {
		this.fetcherName = fetcherName;
	}

	public String getScriptName() {
		return scriptName;
	}

	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}

	public String getFetcherCommand() {
		return fetcherCommand;
	}

	public void setFetcherCommand(String fetcherCommand) {
		this.fetcherCommand = fetcherCommand;
	}

	public JsonArray getMandatoryArgs() {
		return mandatoryArgs;
	}

	public void setMandatoryArgs(JsonArray mandatoryArgs) {
		this.mandatoryArgs = mandatoryArgs;
	}

	public JsonArray getOptionalArgs() {
		return optionalArgs;
	}

	public void setOptionalArgs(JsonArray optionalArgs) {
		this.optionalArgs = optionalArgs;
	}

	

	public int getFetcherType() {
		return fetcherType;
	}

	public void setFetcherType(int fetcherType) {
		this.fetcherType = fetcherType;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
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
