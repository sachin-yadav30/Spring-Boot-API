package org.gn.udip.service.search.vo;

import java.util.ArrayList;
import java.util.List;

import org.gn.udip.model.ServiceMaster;

/**
 * @author Ankita
 *
 */
public class ServiceSearchResult {
	
	private int count;
	
	private List<ServiceMasterVO> services;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<ServiceMasterVO> getServices() {
		return services;
	}

	public void setServices(List<ServiceMasterVO> services) {
		this.services = services;
	}

	public ServiceSearchResult(int count, List<ServiceMaster> serviceMasters) {
		super();
		this.count = count;
		
		List<ServiceMasterVO> list = new ArrayList<ServiceMasterVO>();
		for (ServiceMaster serviceMaster : serviceMasters) {
			list.add(new ServiceMasterVO(serviceMaster.getServiceId(), serviceMaster.getServiceName()));
		}
		this.services = list;
	}

	public ServiceSearchResult() {
		super();
	}
}
