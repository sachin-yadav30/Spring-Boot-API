package org.gn.udip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "parser_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Indexed(index="parsermaster")
public class ParserMaster implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long parserId;
	
	@Column(name = "name")
	@Field(index = Index.YES, store = Store.YES)
	private String parserName;
	
	@Column(name = "mandatory_args")
	@JsonFormat(pattern="String")
	private String mandatoryArgs;
	
	@Column(name = "optional_args")
	@JsonFormat(pattern="String")
	private String optionalArgs;
	
	@Column(name = "scriptname")
	private String scriptName;
	
	@Column(name = "parser_command")
	private String parserCommand;
	
	@Column(name = "isActive")
	@Field(index = Index.YES, store = Store.YES)
	private String isActive="1";
	
	@Column(name = "createdBy")
	@Field(index = Index.YES, store = Store.YES)
	private String createdBy;
	
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createdOn", columnDefinition = "DATETIME")
	@CreationTimestamp
	private Date createdOn;

	@Column(name = "modifiedOn", columnDefinition = "DATETIME")
	@UpdateTimestamp
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
	public String getMandatoryArgs() {
		return mandatoryArgs;
	}
	public void setMandatoryArgs(String mandatoryArgs) {
		this.mandatoryArgs = mandatoryArgs;
	}
	public String getOptionalArgs() {
		return optionalArgs;
	}
	public void setOptionalArgs(String optionalArgs) {
		this.optionalArgs = optionalArgs;
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
