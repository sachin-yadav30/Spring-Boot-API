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

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "service_sample_data")
public class ServiceSampleData implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "job_id")	
	private Long jobId;
	
	@Column(name = "sample_data")	
	@JsonFormat(pattern="String")
	private String sampleData;
	
	@Column(name = "created_on", nullable = false, updatable = false, columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdOn;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getJobId() {
		return jobId;
	}

	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}

	public String getSampleData() {
		return sampleData;
	}

	public void setSampleData(String sampleData) {
		this.sampleData = sampleData;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/*@Override
	public String toString() {
		return jobId.toString();
	}*/
}
