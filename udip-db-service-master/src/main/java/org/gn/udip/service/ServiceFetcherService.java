package org.gn.udip.service;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.FetcherMaster;
import org.gn.udip.model.ServiceFetcherDetail;
import org.gn.udip.model.ServiceFetcherReq;
import org.gn.udip.model.ServiceMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ServiceFetcherService {

	@Autowired
	DataService dataService;

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(ServiceFetcherService.class);

	
	private void validateServiceFetcherLinker(ServiceFetcherReq serviceFetcherReq,boolean isUpdate) throws ValidationException {
		if(serviceFetcherReq.getFetcherId()==null || (serviceFetcherReq.getFetcherId()!=null && serviceFetcherReq.getFetcherId().equals(Long.valueOf(0))))
			throw new ValidationException("Fetcher Id is Null");
		if(isUpdate && (serviceFetcherReq.getModifiedBy()==null || serviceFetcherReq.getModifiedBy().isEmpty()))
			throw new ValidationException("Modified By is Null");
		if (!isUpdate && (serviceFetcherReq.getCreatedBy() == null || serviceFetcherReq.getCreatedBy().isEmpty()))
			throw new ValidationException("Created By is Null");
	}
	
	public void addupdateServiceFetcher(String strReqBean) throws DBException, ValidationException {
		ServiceFetcherDetail bean = null;
		FetcherMaster fetcherBean = new FetcherMaster();
		ServiceMaster serviceBean=new ServiceMaster();
		
		ServiceFetcherReq serviceFetcherReq = null;

		try {
			
			serviceFetcherReq = gson.fromJson(strReqBean, ServiceFetcherReq.class);
			if(serviceFetcherReq.getServiceId()==null || (serviceFetcherReq.getServiceId()!=null && serviceFetcherReq.getServiceId().equals(Long.valueOf(0)))){
				throw new ValidationException("Service Id is Null");
			}
			
			serviceBean.setServiceId(serviceFetcherReq.getServiceId());
			
			bean = dataService.getServiceFetcherDetails(serviceBean);
			
			if (bean == null) {
				validateServiceFetcherLinker(serviceFetcherReq,false);
				bean = new ServiceFetcherDetail();
				bean.setCreatedOn(Calendar.getInstance().getTime());
				bean.setModifiedOn(bean.getCreatedOn());
				bean.setCreatedBy(serviceFetcherReq.getCreatedBy() != null ? serviceFetcherReq.getCreatedBy() : null);
				fetcherBean.setFetcherId(
						serviceFetcherReq.getFetcherId() != null ? serviceFetcherReq.getFetcherId() : null);
				bean.setFetcherMaster(serviceFetcherReq.getFetcherId() != null ? fetcherBean : null);
				bean.setCreatedBy(serviceFetcherReq.getCreatedBy() != null ? serviceFetcherReq.getCreatedBy() : null);
				bean.setServiceFetcherArgs(serviceFetcherReq.getServiceFetcherArgs() != null && !serviceFetcherReq.getServiceFetcherArgs().isJsonNull()
						? serviceFetcherReq.getServiceFetcherArgs().toString() : null);
				bean.setServiceMaster(
						serviceFetcherReq.getServiceId() != null ? serviceBean : null);

			} else {
				validateServiceFetcherLinker(serviceFetcherReq,true);
				fetcherBean
						.setFetcherId(serviceFetcherReq.getFetcherId() != null || serviceFetcherReq.getFetcherId() != 0
								? serviceFetcherReq.getFetcherId() : bean.getFetcherMaster().getFetcherId());
				bean.setFetcherMaster(serviceFetcherReq.getFetcherId() != null ? fetcherBean : null);
				bean.setModifiedBy(serviceFetcherReq.getModifiedBy());
				bean.setModifiedOn(Calendar.getInstance().getTime());
				bean.setServiceFetcherArgs(serviceFetcherReq.getServiceFetcherArgs() != null && !serviceFetcherReq.getServiceFetcherArgs().isJsonNull()
						? serviceFetcherReq.getServiceFetcherArgs().toString() : null);
			}
			dataService.mergeServiceFetcherMapper(bean);
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}
	}

}
