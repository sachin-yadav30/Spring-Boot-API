package org.gn.udip.service;

import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.NotificationLevelEnum;
import org.gn.udip.model.ServiceNotification;
import org.gn.udip.model.ServiceNotificationPojo;
import org.gn.udip.model.UserNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceNotificationService {
	
	@Autowired
	DataService dataService;
	
	private static Logger logger = Logger.getLogger(ServiceNotificationService.class);
	
	@Transactional
	public void addupdateServiceNotificationData(ServiceNotificationPojo serviceNotificationDataReq) throws ValidationException, DBException {

		UserNotification userNotification =null;
		ServiceNotification dbBean = null;
		try {
			validateData(serviceNotificationDataReq);
			dbBean = dataService.getNotificationConfig(serviceNotificationDataReq.getServiceId());
			if(dbBean==null){
				//add new Service-Notification and User-Notification
				userNotification = new UserNotification();
				userNotification.setReceptionList(serviceNotificationDataReq.getReceptionList()!=null && !serviceNotificationDataReq.getReceptionList().isEmpty() ?serviceNotificationDataReq.getReceptionList():"");
				userNotification = dataService.addupdateUserNotification(userNotification);
				dbBean = new ServiceNotification();
				dbBean.setNotifyConf(userNotification);
				dbBean.setServiceId(serviceNotificationDataReq.getServiceId());
				dbBean.setNotifyLevel(NotificationLevelEnum.valueOf(serviceNotificationDataReq.getNotifyLevel()));
				logger.info("Service Notification Insert Service ID " +serviceNotificationDataReq.getServiceId());
			}else{
				//update existing Service-Notification and User-Notification
				dbBean.getNotifyConf().setReceptionList(serviceNotificationDataReq.getReceptionList()!=null && !serviceNotificationDataReq.getReceptionList().isEmpty()?serviceNotificationDataReq.getReceptionList():"");
				userNotification = dataService.addupdateUserNotification(dbBean.getNotifyConf());
				dbBean.setNotifyConf(userNotification);
				dbBean.setNotifyLevel(NotificationLevelEnum.valueOf(serviceNotificationDataReq.getNotifyLevel()));
				dbBean.setServiceId(serviceNotificationDataReq.getServiceId());
				logger.info("Service Notification Update Service ID " +serviceNotificationDataReq.getServiceId());
			}
			dataService.insertupdateServiceNotification(dbBean);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion/update of ServiceNotificationData");
			throw new ValidationException("422", "Validation Failure - Insertion/Update failed ");
		}
		
	}
	
	private void validateData(ServiceNotificationPojo serviceNotificationDataReq) throws ValidationException
	{
		if(serviceNotificationDataReq.getServiceId()==null)
			throw new ValidationException("ServiceId is Null, expected to provide ServiceId");
	
		if(!nullAndEmptyCheck(serviceNotificationDataReq.getNotifyLevel()))
			throw new ValidationException("NotificationLevel is Null or Empty, expected to provide NotificationLevel");
	}
	
	private boolean nullAndEmptyCheck(String data) {
		
		boolean isDataPresent=false;
		if(data != null && !data.isEmpty())
		{
			isDataPresent=true;
		}
		
		return isDataPresent;
	}

}
