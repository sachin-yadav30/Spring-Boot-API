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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "service_fetcher_detail")
@JsonAutoDetect
@Indexed(index="serviceFetcherDetail")
public class ServiceFetcherDetail implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long mapperId;

	@OneToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "service_master_id")
	private ServiceMaster serviceMaster;
	
	@ManyToOne(optional=false,fetch=FetchType.LAZY)
	@JoinColumn(name="fetcher_master_id")
	private FetcherMaster fetcherMaster;
	
	@Column(name = "service_fetcher_args")	
	@JsonFormat(pattern="String")
	@Field(index = Index.YES, store = Store.YES)
	private String serviceFetcherArgs;
	
	@Column(name = "createdBy")	
	private String createdBy;
	
	@Column(name = "modifiedBy")	
	private String modifiedBy;
	
	@Column(name = "createdOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdOn;
	
	@Column(name = "modifiedOn")
	@Temporal(TemporalType.TIMESTAMP)
	private Date modifiedOn;
	
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

	public FetcherMaster getFetcherMaster() {
		return fetcherMaster;
	}

	public void setFetcherMaster(FetcherMaster fetcherDetail) {
		this.fetcherMaster = fetcherDetail;
	}

	public String getServiceFetcherArgs() {
		return serviceFetcherArgs;
	}

	public void setServiceFetcherArgs(String serviceFetcherArgs) {
		this.serviceFetcherArgs = serviceFetcherArgs;
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
