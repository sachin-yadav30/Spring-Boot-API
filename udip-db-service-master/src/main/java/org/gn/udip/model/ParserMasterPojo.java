package org.gn.udip.model;

import java.util.Date;

import com.google.gson.JsonArray;

public class ParserMasterPojo {
	
	private Long parserId;
	private String parserName;
	private JsonArray mandatoryArgs;
	private JsonArray optionalArgs;
	private String scriptName;
	private String parserCommand;
	private String createdBy;
	private String modifiedBy;
	private Date createdOn;
	private Date modifiedOn;
	
	public Long getParserId() {
		return parserId;
	}
	public void setParserId(Long parserId) {
		this.parserId = parserId;
	}
	public String getParserName() {
		return parserName;
	}
	public void setParserName(String parserName) {
		this.parserName = parserName;
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
	public String getScriptName() {
		return scriptName;
	}
	public void setScriptName(String scriptName) {
		this.scriptName = scriptName;
	}
	public String getParserCommand() {
		return parserCommand;
	}
	public void setParserCommand(String parserCommand) {
		this.parserCommand = parserCommand;
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
