package org.gn.udip.controller;

import org.apache.log4j.Logger;
import org.gn.udip.model.ServiceParserDetail;
import org.gn.udip.model.ServiceParserDetailResponsePojo;
import org.gn.udip.model.JobStatusModel;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.model.TriggerFetcherResponseModel;
import org.gn.udip.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class JobController {

	@Autowired
	JobService jobService;

	private static Logger logger = Logger.getLogger(JobController.class);

	@GetMapping("/jobs/parserdetail")
	public ServiceParserDetailResponsePojo getParserDetail(@RequestParam("jobId") String jobId) throws Exception {

		logger.info("REQUEST ARRIVED : GET PARSER DETAILS  " + jobId);
		return jobService.getParserDetails(jobId);

	}

	@PostMapping("/jobstatus")
	public RequestStatus updateJobStatus(@RequestBody JobStatusModel jobStatusReq) throws Exception {

		logger.info("REQUEST ARRIVED : POST JOB UPDATE   " + jobStatusReq.getJobId());
		jobStatusReq = jobService.updateJob(jobStatusReq);
		jobService.notifySubsciber(jobStatusReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}

	/* Added by Kartik */
	@GetMapping("/triggerfetcher")
	public TriggerFetcherResponseModel triggerFetcher(@RequestParam("serviceId") String serviceId,
			@RequestParam("userId") String userId, @RequestParam(value = "isTestJob", required = false) boolean isTestJob) throws Exception {

		logger.info("REQUEST ARRIVED : GET FETCHER KAFKA JOB DETAILS FOR SERVICE_ID: " + serviceId + " AND USER_ID: "+ userId);
		
		return jobService.initiateTriggerFetcher(Long.valueOf(serviceId), Long.valueOf(userId), isTestJob);
	}

	/* Added by Kartik */
	@PostMapping("/testData")
	public RequestStatus insertTestRunData(@RequestParam("jobId") String jobId, @RequestBody String sampleData) throws Exception {

		logger.info("REQUEST ARRIVED : POST TEST DATA FOR JOB_ID : " + jobId);
		jobService.addTestRunData(Long.valueOf(jobId), sampleData);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
}