package org.gn.udip.controller;

import org.apache.log4j.Logger;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.model.ServiceCreationResponse;
import org.gn.udip.model.ServiceMasterHistoryReq;
import org.gn.udip.model.ServiceMasterPojo;
import org.gn.udip.model.ServiceNotificationPojo;
import org.gn.udip.service.ServiceFetcherService;
import org.gn.udip.service.ServiceHistoryService;
import org.gn.udip.service.ServiceMasterService;
import org.gn.udip.service.ServiceNotificationService;
import org.gn.udip.service.ServiceParserService;
import org.gn.udip.service.ServiceScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/service")
public class ServiceController {
	
	@Autowired
	ServiceMasterService serviceMasterService;
	
	@Autowired
	ServiceParserService serviceParserService;
	
	@Autowired
	ServiceNotificationService serviceNotificationService;
	
	@Autowired
	ServiceScheduleService serviceScheduleService;
	
	@Autowired
	ServiceFetcherService serviceFetcherService;
	
	@Autowired
	ServiceHistoryService serviceHistoryService;
	
	private static Logger logger = Logger.getLogger(ServiceController.class);
	
	@PostMapping("/add")
	public ServiceCreationResponse addServiceData(@RequestBody ServiceMasterPojo serviceDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : INSERT SERVICE DATA HAVING SERVICE NAME : " + serviceDataReq.getServiceName());
		Long serviceId=serviceMasterService.addServiceData(serviceDataReq);
		
		return new ServiceCreationResponse("00", "Request Successfuly Processed",serviceId);
	}
	
	@PostMapping("/update")
	public RequestStatus updateServiceData(@RequestBody ServiceMasterPojo serviceDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : UPDATE SERVICE DATA HAVING SERVICE ID : " + serviceDataReq.getServiceId());
		serviceMasterService.updateServiceData(serviceDataReq,false,false);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/update/servicename")
	public RequestStatus updateServiceNameData(@RequestBody ServiceMasterPojo serviceDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : UPDATE SERVICE NAME DATA HAVING SERVICE ID : " + serviceDataReq.getServiceId());
		serviceMasterService.updateServiceData(serviceDataReq,true,false);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/update/schedule")
	public RequestStatus updateServiceSchedulableData(@RequestBody ServiceMasterPojo serviceDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : UPDATE SERVICE SCHEDULE DATA HAVING SERVICE ID : " + serviceDataReq.getServiceId());
		serviceMasterService.updateServiceData(serviceDataReq,false,true);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/delete")
	public RequestStatus deleteServiceData(@RequestBody ServiceMasterPojo serviceDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : DELETE SERVICE DATA HAVING SERVICE ID : " + serviceDataReq.getServiceId());
		serviceMasterService.deleteServiceData(serviceDataReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/parser/addupdate")
	public RequestStatus addupdateServiceParserData(@RequestBody String serviceParserDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : INSERT/UPDATE SERVICE PARSER DATA : " + serviceParserDataReq);
		serviceParserService.addupdateServiceParserData(serviceParserDataReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/notification/addupdate")
	public RequestStatus addupdateServiceNotificationData(@RequestBody ServiceNotificationPojo serviceNotificationDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : INSERT/UPDATE SERVICE NOTIFICATION DATA HAVING SERVICE ID : " + serviceNotificationDataReq.getServiceId());
		serviceNotificationService.addupdateServiceNotificationData(serviceNotificationDataReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/schedule/addupdate")
	public RequestStatus scheduleService(@RequestBody String serviceScheduleReq) throws Exception {
		logger.info("REQUEST ARRIVED : POST Service Schedule Config");
		serviceScheduleService.mergeServiceScheduleMaster(serviceScheduleReq);
		return new RequestStatus("00","Request Successfuly Processed");
	}
	
	@PostMapping("/fetcher/addupdate")
	public RequestStatus configureServiceFetcher(@RequestBody String serviceFetcherReq) throws Exception {
		logger.info("REQUEST ARRIVED : POST Service Fetcher Config");
		serviceFetcherService.addupdateServiceFetcher(serviceFetcherReq);
		return new RequestStatus("00", "Fetcher Successfuly Updated");
	}
	
	@PostMapping("/history")
	public RequestStatus logHistory(@RequestBody String serviceHistoryReq) throws Exception {
		logger.info("REQUEST ARRIVED : POST Service History");
		serviceHistoryService.insertServiceHistoryMaster(serviceHistoryReq);
		return new RequestStatus("00", "Service History Successfuly Recorded");
	}

}
