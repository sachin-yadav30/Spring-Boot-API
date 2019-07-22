package org.gn.udip.service;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.ServiceScheduleMaster;
import org.gn.udip.model.ServiceScheduleMasterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ServiceScheduleService {
	
	@Autowired
	DataService dataService;

	private Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(ServiceScheduleService.class);

	public void validateServiceScheduleMasterReq(ServiceScheduleMasterReq req, boolean isUpdate)
			throws ValidationException {
		System.out.println("Update Flag "+isUpdate);
		

		if (isUpdate && (req.getModifiedBy() == null || req.getModifiedBy().isEmpty()))
			throw new ValidationException("Modified By is Null");

		if (!isUpdate && (req.getCreatedBy() == null || req.getCreatedBy().isEmpty()))
			throw new ValidationException("Created By is Null");

	}

	public void mergeServiceScheduleMaster(String reqBeanStr) throws DBException, ValidationException {
		ServiceScheduleMaster dbBean = null;
		ServiceScheduleMasterReq serviceScheduleMasterReq = null;
		try {
			serviceScheduleMasterReq = gson.fromJson(reqBeanStr, ServiceScheduleMasterReq.class);
			if (serviceScheduleMasterReq.getServiceId() == null || (serviceScheduleMasterReq.getServiceId() !=null && serviceScheduleMasterReq.getServiceId() == 0))
				throw new ValidationException("ServiceId is Null");
			dbBean = dataService.getServiceScheduleMaster(serviceScheduleMasterReq.getServiceId());
			if (dbBean == null) {
				validateServiceScheduleMasterReq(serviceScheduleMasterReq, false);
				dbBean = new ServiceScheduleMaster();
				dbBean.setServiceId(serviceScheduleMasterReq.getServiceId());
				dbBean.setModifiedBy(serviceScheduleMasterReq.getCreatedBy());
				dbBean.setCreatedOn(Calendar.getInstance().getTime());
				dbBean.setModifiedOn(dbBean.getCreatedOn());
				dbBean.setCustomFrequency(serviceScheduleMasterReq.getCustomFrequency() != null && !serviceScheduleMasterReq.getCustomFrequency().isJsonNull()
						? serviceScheduleMasterReq.getCustomFrequency().toString() : null);
				dbBean.setFrequency(serviceScheduleMasterReq.getFrequency() != null ? serviceScheduleMasterReq.getFrequency() : null);
				dbBean.setIsActive(
						serviceScheduleMasterReq.getIsActive() ==1 ? true : false);
				dbBean.setStartTime(serviceScheduleMasterReq.getStartTime() != null
						? serviceScheduleMasterReq.getStartTime() : null);
				dbBean.setTimezone(
						serviceScheduleMasterReq.getTimezone() != null ? serviceScheduleMasterReq.getTimezone() : null);
				logger.info("Processing Service Schedule Request Insert serviceId : " + dbBean.getServiceId());
			} else {
				validateServiceScheduleMasterReq(serviceScheduleMasterReq, true);
				dbBean.setCustomFrequency(serviceScheduleMasterReq.getCustomFrequency() != null && !serviceScheduleMasterReq.getCustomFrequency().isJsonNull()
						? serviceScheduleMasterReq.getCustomFrequency().toString() : null);
				dbBean.setFrequency(serviceScheduleMasterReq.getFrequency() != null
						? serviceScheduleMasterReq.getFrequency() : dbBean.getFrequency());
				dbBean.setIsActive(
						serviceScheduleMasterReq.getIsActive() ==1 ? true : false);
				dbBean.setModifiedBy(serviceScheduleMasterReq.getModifiedBy() != null
						? serviceScheduleMasterReq.getModifiedBy() : "");
				// dbBean.setModifiedOn(Calendar.getInstance().getTime());
				if (serviceScheduleMasterReq.getStartTime() != null)
					dbBean.setStartTime(serviceScheduleMasterReq.getStartTime());
				else
					dbBean.setStartTime(dbBean.getStartTime());
				dbBean.setTimezone(serviceScheduleMasterReq.getTimezone() != null
						? serviceScheduleMasterReq.getTimezone() : dbBean.getTimezone());
				logger.info("Processing Service Schedule Request Update serviceId : " + dbBean.getServiceId());
			}
			dbBean = dataService.mergeServiceScheduleMaster(dbBean);
			logger.info("Process Completed Service Schedule MapperId" + dbBean.getId());
		} catch (ValidationException e) {
			logger.error(e);
			throw e;
		} catch (DBException e) {
			logger.error(e);
			throw e;
		} catch (Exception e) {
			logger.error(e);
			throw e;
		}

	}

}
