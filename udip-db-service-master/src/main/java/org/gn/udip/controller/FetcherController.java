package org.gn.udip.controller;

import org.apache.log4j.Logger;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.service.FetcherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FetcherController {
	
	@Autowired
	FetcherService fetcherService;
	
	private static Logger logger = Logger.getLogger(FetcherController.class);
	
	@PostMapping("/fetcher/add")
	public RequestStatus createFetcher(@RequestBody String fetcherReqJsonString) throws Exception {
		logger.info("REQUEST ARRIVED : POST FETCHER ADD "+fetcherReqJsonString);
		fetcherService.addFetcher(fetcherReqJsonString);
		return new RequestStatus("00", "Fetcher Successfuly Added");
	}
	
	@PostMapping("/fetcher/update")
	public RequestStatus updateFetcher(@RequestBody String fetcherReqJsonString) throws Exception {
		logger.info("REQUEST ARRIVED : POST FETCHER Update "+fetcherReqJsonString);
		fetcherService.updateFetcherMaster(fetcherReqJsonString);
		return new RequestStatus("00", "Fetcher Successfuly Updated");
	}
	
	@PostMapping("/fetcher/delete")
	public RequestStatus deleteFetcher(@RequestBody String fetcherReqJsonString) throws Exception {
		logger.info("REQUEST ARRIVED : POST FETCHER Delete  Fetcher Id");
		fetcherService.deleteFetcher(fetcherReqJsonString);
		return new RequestStatus("00", "Fetcher Successfuly Deleted");
	}
	
	

}
