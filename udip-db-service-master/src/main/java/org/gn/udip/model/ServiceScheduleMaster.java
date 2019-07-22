package org.gn.udip.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "service_schedule_master")
public class ServiceScheduleMaster implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "service_master_id")
	private Long serviceId;
	
	@Column(name = "frequency")
	private String frequency;
	
	@Column(name = "custom_frequency")
	private String customFrequency;
	
	@Column(name = "isActive")
	private Boolean isActive;
	
	@Column(name = "timezone")
	private String timezone;
	
	@Column(name = "startTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;
	
	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createdOn")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdOn;
	
	@Column(name = "modifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifiedOn;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getCustomFrequency() {
		return customFrequency;
	}

	public void setCustomFrequency(String customFrequency) {
		this.customFrequency = customFrequency;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}

	public String getTimezone() {
		return timezone;
	}

	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public Date getStartTime() {
				
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setStartTime(String startTime) {
		try {
			this.startTime =  new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(startTime);
		} catch (Exception e) {
			this.startTime = null;
		}
		
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
