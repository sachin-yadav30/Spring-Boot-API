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
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="service_master")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Indexed(index="servicemaster")
public class ServiceMaster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
	private Long serviceId;
	
	@Column(name = "name")	
	@Field(index = Index.YES, store = Store.YES)
	private String serviceName;
	
	
	@Column(name = "unique_id")	
	@Field(index = Index.YES, store = Store.YES)
	private String uniqueId;
	
	@Column(name = "vertical")
	private String vertical;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name="category")
	private CategoryModel category;
	
	@Column(name = "owner")
	@Field(index = Index.YES, store = Store.YES)
	private String owner;
	
	@Column(name = "status")	
	private String status="1";
	
	@Column(name = "charset")	
	private String charset;
	
	@Column(name = "fetcher_run_status")	
	private String fetcherRunStatus;
	
	@Column(name = "isActive")
	@Field(index = Index.YES, store = Store.YES)
	private String isActive="1";
	
	@Column(name = "isSchedulable")	
	private String isSchedulable="0";
	
	@Column(name = "isConcatenatedData")
	private Boolean isConcatenatedData=false;
	
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
	
	@JsonIgnore
	@OneToOne(mappedBy="serviceMaster")
	@IndexedEmbedded(depth=2)
	private ServiceFetcherDetail serviceFetcherDetail;
	
	@JsonIgnore
	@OneToOne(mappedBy="serviceMaster")
	@IndexedEmbedded(depth=2)
	private ServiceParserDetail serviceParserDetail;
	
	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}
	
	public String getVertical() {
		return vertical;
	}

	public void setVertical(String vertical) {
		this.vertical = vertical;
	}
	
	public CategoryModel getCategory() {
		return category;
	}

	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	
	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFetcherRunStatus() {
		return fetcherRunStatus;
	}

	public void setFetcherRunStatus(JobStatusEnum fetcherRunStatus) {
		this.fetcherRunStatus = fetcherRunStatus.toString();
	}
	
	public void setFetcherRunStatus(String fetcherRunStatus) {
		this.fetcherRunStatus = fetcherRunStatus;
	}

	public Boolean getIsConcatenatedData() {
		return isConcatenatedData;
	}
	
	public void setIsConcatenatedData(Boolean isConcatenatedData) {
		this.isConcatenatedData = isConcatenatedData;
	}
	
	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public String getIsSchedulable() {
		return isSchedulable;
	}

	public void setIsSchedulable(String isSchedulable) {
		this.isSchedulable = isSchedulable;
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

	public ServiceFetcherDetail getServiceFetcherDetail() {
		return serviceFetcherDetail;
	}

	public void setServiceFetcherDetail(ServiceFetcherDetail serviceFetcherDetail) {
		this.serviceFetcherDetail = serviceFetcherDetail;
	}

	public ServiceParserDetail getServiceParserDetail() {
		return serviceParserDetail;
	}

	public void setServiceParserDetail(ServiceParserDetail serviceParserDetail) {
		this.serviceParserDetail = serviceParserDetail;
	}
}