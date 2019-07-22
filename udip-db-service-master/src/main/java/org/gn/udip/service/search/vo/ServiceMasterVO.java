package org.gn.udip.service.search.vo;

public class ServiceMasterVO{
	
	private String name;
	private Long id;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ServiceMasterVO(Long id, String serviceName) {
		super();
		this.name = serviceName;
		this.id = id;
	}
	public ServiceMasterVO() {
		super();
	}
}
