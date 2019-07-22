package org.gn.udip.service;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.ServiceMasterHistory;
import org.gn.udip.model.ServiceMasterHistoryReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ServiceHistoryService {
	
	@Autowired
	DataService dataService;

	private static Logger logger = Logger.getLogger(ServiceHistoryService.class);

	public void insertServiceHistoryMaster(String reqBeanStr) throws DBException, ValidationException {
		
		Gson gson = new Gson(); 
		try{
	        ServiceMasterHistoryReq reqBean = gson.fromJson(reqBeanStr, ServiceMasterHistoryReq.class);
	        
			if (reqBean.getChange() == null || reqBean.getServiceId()==null || reqBean.getServiceId() == 0 || reqBean.getCreatedBy() == null) {
				throw new ValidationException("400", "Bad request");
			}
			ServiceMasterHistory bean = new ServiceMasterHistory();
			bean.setChange(reqBean.getChange() != null ? reqBean.getChange().toString() : null);
			bean.setCreatedBy(reqBean.getCreatedBy() != null ? reqBean.getCreatedBy() : null);
			bean.setCreatedOn(Calendar.getInstance().getTime());
			bean.setServiceId(reqBean.getServiceId() != null ? reqBean.getServiceId() : null);
			bean.setType(reqBean.getType() != null ? reqBean.getType() : null);
			dataService.insertServiceHistory(bean);
		}catch(Exception e){
			logger.error(e);
			throw new ValidationException("400","Bad Request");
		}
	}

}
