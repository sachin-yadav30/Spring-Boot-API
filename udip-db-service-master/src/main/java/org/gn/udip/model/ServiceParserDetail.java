package org.gn.udip.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "service_parser_detail")
@JsonAutoDetect
@Indexed(index="serviceParserDetail")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ServiceParserDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long mapperId;
	
	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_master_id")
	private ServiceMaster serviceMaster;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name="parser_master_id")
	private ParserMaster parserMaster;
	
	@Column(name = "service_parser_args")
	@JsonFormat(pattern="String")
	@Field(index = Index.YES, store = Store.YES)
	private String serviceParserArgs;

	@Column(name = "createdBy")
	private String createdBy;
	
	@Column(name = "modifiedBy")
	private String modifiedBy;
	
	@Column(name = "createdOn", columnDefinition = "DATETIME")
	@CreationTimestamp
	private Date createdOn;

	@Column(name = "modifiedOn", columnDefinition = "DATETIME")
	@UpdateTimestamp
	private Date modifiedOn;

	public ParserMaster getParserMaster() {
		return parserMaster;
	}

	public void setParserMaster(ParserMaster parserDetail) {
		this.parserMaster = parserDetail;
	}

	public Long getMapperId() {
		return mapperId;
	}

	public void setMapperId(Long mapperId) {
		this.mapperId = mapperId;
	}

	public ServiceMaster getServiceMaster() {
		return serviceMaster;
	}

	public void setServiceMaster(ServiceMaster serviceDetail) {
		this.serviceMaster = serviceDetail;
	}

	public String getServiceParserArgs() {
		return serviceParserArgs;
	}

	public void setServiceParserArgs(String serviceParserArgs) {
		this.serviceParserArgs = serviceParserArgs;
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