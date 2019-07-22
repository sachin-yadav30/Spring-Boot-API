package org.gn.udip.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "service_job_status")
public class JobStatusModel implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long jobId;
	
	@Column(name = "job_id")
	private String uniqueJobId;
	
	@Column(name = "service_master_id")
	private Long serviceId;

	@Column(name = "fetcher_startedBy")
	private String fetcherStartedBy;
	
	@Column(name = "parser_startedBy")
	private String parserStartedBy;
	
	@Column(name = "cron_startTime")
	@Temporal(TemporalType.TIMESTAMP)
	private Date cronStartTime;

	@Column(name = "fetcher_startTime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fetcherStartTime;

	@Column(name = "fetcher_endTime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fetcherEndTime;

	@Column(name = "parser_startTime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date parserStartTime;

	@Column(name = "parser_endTime", columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date parserEndTime;

	@Column(name = "fetched_file_count")
	private String fetchedFileCount;

	@Column(name = "fetched_file_names")
	private String fetchedFileNames;

	@Column(name = "message_count")
	private String messageCount;
	
	@Column(name = "blob_ref_id")
	private String blobRefId;
	
	@Column(name = "blob_ref_url")
	private String blobRefUrl;
	
	
	@NotNull
	@Column(name = "service_job_status")
	private String serviceJobStatus;

	@Column(name = "stack_trace")
	private String stackTrace;

	@Column(name = "createdBy", updatable = false)
	private Long createdBy;

	
	@Column(name = "createdOn", nullable = false, updatable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdOn;

	@Column(name = "modifiedOn", nullable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date modifiedOn;
	
	
	@Column(name = "isTestJob")
	private Boolean isTestJob;

	public Boolean getIsTestJob() {
		return isTestJob;
	}

	public void setIsTestJob(Boolean isTestJob) {
		this.isTestJob = isTestJob;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getFetchedFileNames() {
		return fetchedFileNames;
	}

	public void setFetchedFileNames(String fetchedFileNames) {
		this.fetchedFileNames = fetchedFileNames;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public Date getCronStartTime() {
		return cronStartTime;
	}

	public void setCronStartTime(Date cronStartTime) {
		this.cronStartTime = cronStartTime;
	}

	public Date getFetcherStartTime() {
		return fetcherStartTime;
	}

	public void setFetcherStartTime(Date fetcherStartTime) {
		this.fetcherStartTime = fetcherStartTime;
	}

	public Date getFetcherEndTime() {
		return fetcherEndTime;
	}

	public void setFetcherEndTime(Date fetcherEndTime) {
		this.fetcherEndTime = fetcherEndTime;
	}

	public Date getParserStartTime() {
		return parserStartTime;
	}

	public void setParserStartTime(Date parserStartTime) {
		this.parserStartTime = parserStartTime;
	}

	public Date getParserEndTime() {
		return parserEndTime;
	}

	public void setParserEndTime(Date parserEndTime) {
		this.parserEndTime = parserEndTime;
	}

	public String getFetchedFileCount() {
		return fetchedFileCount;
	}

	public void setFetchedFileCount(String fetchedFileCount) {
		this.fetchedFileCount = fetchedFileCount;
	}

	

	public String getMessageCount() {
		return messageCount;
	}

	public void setMessageCount(String messageCount) {
		this.messageCount = messageCount;
	}

	public String getBlobRefId() {
		return blobRefId;
	}

	public void setBlobRefId(String blobRefId) {
		this.blobRefId = blobRefId;
	}

	public String getBlobRefUrl() {
		return blobRefUrl;
	}

	public void setBlobRefUrl(String blobRefUrl) {
		this.blobRefUrl = blobRefUrl;
	}

	public JobStatusEnum getServiceJobStatus() throws Exception {
		
		try {
			return  JobStatusEnum.fromValue(this.serviceJobStatus);
		} catch (Exception e) {
			throw e;
		}
		
	}
	
	public void setServiceJobStatus(JobStatusEnum serviceJobStatus) {
		this.serviceJobStatus = serviceJobStatus.toString();
	}

	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
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

	public String getFetcherStartedBy() {
		return fetcherStartedBy;
	}

	public void setFetcherStartedBy(String fetcherStartedBy) {
		this.fetcherStartedBy = fetcherStartedBy;
	}

	public String getParserStartedBy() {
		return parserStartedBy;
	}

	public void setParserStartedBy(String parserStartedBy) {
		this.parserStartedBy = parserStartedBy;
	}

	public String getUniqueJobId() {
		return uniqueJobId;
	}

	public void setUniqueJobId(String uniqueJobId) {
		this.uniqueJobId = uniqueJobId;
	}

	

	

	
}
