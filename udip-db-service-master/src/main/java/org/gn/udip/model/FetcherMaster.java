package org.gn.udip.model;

import java.io.IOException;
import java.util.Date;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.JsonProcessingException;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@Table(name = "fetcher_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Indexed(index="fetchermaster")
public class FetcherMaster implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
	private Long fetcherId;

	@Column(name = "name")
	@Field(index = Index.YES, store = Store.YES)
	private String fetcherName;
	
	@Column(name = "scriptname")	
	private String scriptName;
	
	@Column(name = "fetcher_command")	
	private String fetcherCommand;
	
	//@Convert(converter = JpaConverterJson.class)
	@Column(name = "mandatory_args")	
	private String mandatoryArgs;
	
	//@Convert(converter = JpaConverterJson.class)
	@Column(name = "optional_args")	
	private String optionalArgs;

	@Column(name = "fetcher_type")
	private int fetcherType;
	
	@Column(name = "isActive")
	@Field(index = Index.YES, store = Store.YES)
	private String isActive;
	
	@Column(name = "createdBy")
	@Field(index = Index.YES, store = Store.YES)
	private String createdBy;
		
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createdOn")
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "modifiedOn")
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
		
	public String getFetcherName() {
		return fetcherName;
	}

	public void setFetcherName(String fetcherName) {
		this.fetcherName = fetcherName;
	}

	public String getMandatoryArgs() {
		return mandatoryArgs;
	}

	public void setMandatoryArgs(String mandatoryArgs) throws JsonProcessingException, IOException {
		this.mandatoryArgs = mandatoryArgs;
	}

	public String getOptionalArgs() {
		return optionalArgs;
	}

	public void setOptionalArgs(String optionalArgs) throws JsonProcessingException, IOException {
		this.optionalArgs = optionalArgs ;
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

	public Long getFetcherId() {
		return fetcherId;
	}

	public void setFetcherId(Long fetcherId) {
		this.fetcherId = fetcherId;
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
	
}
