package org.gn.udip.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.avro.Schema;
import org.apache.avro.reflect.ReflectData;
import org.apache.log4j.Logger;
import org.gn.udip.config.AppProperties;
import org.gn.udip.exception.DBException;
import org.gn.udip.model.Email;
import org.gn.udip.model.FetcherKafkaDetail;
import org.gn.udip.model.ServiceParserDetail;
import org.gn.udip.model.ServiceParserDetailResponsePojo;
import org.gn.udip.model.JobStatusEnum;
import org.gn.udip.model.JobStatusModel;
import org.gn.udip.model.NotificationLevelEnum;
import org.gn.udip.model.ParserDetailResponsePojo;
import org.gn.udip.model.Records;
import org.gn.udip.model.ServiceFetcherDetail;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.model.ServiceNotification;
import org.gn.udip.model.TriggerFetcherResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;


@Service
public class JobService {

	@Autowired
	DataService dataService;

	@Autowired
	EmailService emailService;

	private static Logger logger = Logger.getLogger(JobService.class);

	public void notifySubsciber(JobStatusModel jobStatusReq) {

		Email mail = null;
		ServiceNotification servNotify = null;
		ServiceMaster service = null;
		StringBuilder message = new StringBuilder();

		try {
			servNotify = dataService.getNotificationConfig(jobStatusReq.getServiceId());
			if (servNotify != null && !servNotify.getNotifyConf().getReceptionList().isEmpty()) {
				service = dataService.getService(jobStatusReq.getServiceId());
				mail = new Email();
				mail.setFrom(AppProperties.MAILER_ID);
				mail.setSubject(AppProperties.MAILER_SUBJECT);
				mail.setTo(servNotify.getNotifyConf().getReceptionList());
				message.append("This is to intimate that​ ​​a service has been updated with following status<br>"

						+ " <br> SERVICE NAME : " + service.getServiceName() + " <br> JOB ID : "
						+ jobStatusReq.getUniqueJobId() + " <br> JOB STATUS : " + jobStatusReq.getServiceJobStatus());

				mail.setContent(message.toString());

				if (servNotify.getNotifyLevel() == NotificationLevelEnum.ALL
						&& (jobStatusReq.getServiceJobStatus() == JobStatusEnum.PARSER_COMPLETED
								|| jobStatusReq.getServiceJobStatus() == JobStatusEnum.PARSER_FAILED
								|| jobStatusReq.getServiceJobStatus() == JobStatusEnum.FETCHER_FAILED)) {
					emailService.sendMail(mail);
				} else if (servNotify.getNotifyLevel() == NotificationLevelEnum.FAILURE
						&& (jobStatusReq.getServiceJobStatus() == JobStatusEnum.PARSER_FAILED
								|| jobStatusReq.getServiceJobStatus() == JobStatusEnum.FETCHER_FAILED)) {
					emailService.sendMail(mail);
				} else {
					logger.info("NOTIFICATION DEFFERED : SERVICE ID   " + jobStatusReq.getServiceId());
					throw new DBException("Invalid Service Id Or Notification List is empty");
				}

			} else {
				logger.info("NOTIFICATION NOT CONFIGURED : SERVICE ID   " + jobStatusReq.getServiceId());
			}
		} catch (DBException e) {
			logger.error("Database Exception thrown from DbService : " + jobStatusReq.getServiceId());
			logger.error(e);
		} catch (Exception e) {
			logger.error("ERROR SENDING NOTIFICATION : SERVICE ID   " + jobStatusReq.getServiceId());
			logger.error(e);
		} finally {

			mail = null;
			service = null;
			servNotify = null;
		}
	}

	public JobStatusModel updateJob(JobStatusModel jobStatusReq) throws Exception {
		JobStatusModel jobStatusResp =null;
		if (jobStatusReq.getServiceJobStatus() != null
				&& jobStatusReq.getServiceJobStatus() == JobStatusEnum.FETCHER_RUNNING)
			jobStatusReq.setFetcherStartTime(new Date());
		if (jobStatusReq.getServiceJobStatus() != null
				&& jobStatusReq.getServiceJobStatus() == JobStatusEnum.FETCHER_COMPLETED)
			jobStatusReq.setFetcherEndTime(new Date());
		if (jobStatusReq.getServiceJobStatus() != null
				&& jobStatusReq.getServiceJobStatus() == JobStatusEnum.FETCHER_FAILED)
			jobStatusReq.setFetcherEndTime(new Date());
		if (jobStatusReq.getServiceJobStatus() != null
				&& jobStatusReq.getServiceJobStatus() == JobStatusEnum.PARSER_RUNNING)
			jobStatusReq.setParserStartTime(new Date());
		if (jobStatusReq.getServiceJobStatus() != null
				&& jobStatusReq.getServiceJobStatus() == JobStatusEnum.PARSER_COMPLETED)
			jobStatusReq.setParserEndTime(new Date());
		if (jobStatusReq.getServiceJobStatus() != null
				&& jobStatusReq.getServiceJobStatus() == JobStatusEnum.PARSER_FAILED)
			jobStatusReq.setParserEndTime(new Date());
		
		jobStatusResp = dataService.updateOneJobStatus(jobStatusReq);
		
		return jobStatusResp;

	}

	/* Added by Kartik */
	@Transactional
	public TriggerFetcherResponseModel initiateTriggerFetcher(Long serviceId, Long userId, Boolean isTestJob) throws Exception {
		
		TriggerFetcherResponseModel triggerFetcherResponseModel = new TriggerFetcherResponseModel();
		FetcherKafkaDetail fetcherKafkaDetailObj = new FetcherKafkaDetail();
		ServiceMaster smBean =null;
		JobStatusModel jobStatusInsertReq =null;
		ServiceFetcherDetail sfdBean =null;

		try {
		
			logger.info("DataService for Fetching JobUniqueId has been intiated.");
			smBean = dataService.getService(serviceId);
			logger.info("DataService for fetching JobUniqueId has been completed successfully.");
			logger.info("Concatination of JobUniqueId with CurrentTimestamp has been intiated.");
			String jobUniqueId = smBean.getUniqueId() + "_" + getCurrentTimestamp();
			logger.info("Concatinated JobUniqueId is: " + jobUniqueId);
	
			jobStatusInsertReq = new JobStatusModel();
			logger.info("DataService for Inserting new record in ServiceJobStatus has been intiated.");
			Long jobId = dataService.createJob(jobStatusInsertReq, jobUniqueId, serviceId, userId, isTestJob).getJobId();
			logger.info("DataService for inserting new record in ServiceJobStatus has been completed successfully.");
	
			logger.info("DataService for Updating FetcherRunStatus in ServiceMaster has been intiated.");
			dataService.updateFetcherRunStatus(serviceId);
			logger.info("DataService for Updating FetcherRunStatus in ServiceMaster has been completed successfully.");
	
			logger.info("DataService for Fetching ServiceFetcherDetails has been intiated.");
			sfdBean = dataService.getServiceFetcherDetails(smBean);
			if (sfdBean == null)
				throw new DBException("404", "Invalid Service Id - No such ServiceFetcherDetails found for that particular Service Id in ServiceFetcherDetail table");
			logger.info("DataService for fetching ServiceFetcherDetails has been completed successfully.");
	
			logger.info("Constructing FetcherKafkaDetail object.");
			fetcherKafkaDetailObj.setJobId(String.valueOf(jobId));
			fetcherKafkaDetailObj.setServiceId(String.valueOf(serviceId));
			fetcherKafkaDetailObj.setCategory(String.valueOf(smBean.getCategory().getCategoryId()));
			fetcherKafkaDetailObj.setBlobtype(smBean.getCategory().getInputBlobType());
			fetcherKafkaDetailObj.setFetcherId(String.valueOf(sfdBean.getFetcherMaster().getFetcherId()));
			fetcherKafkaDetailObj.setFetcherCommand(sfdBean.getFetcherMaster().getFetcherCommand());
			fetcherKafkaDetailObj.setServiceFetcherArgs(sfdBean.getServiceFetcherArgs());
			fetcherKafkaDetailObj.setScriptName(sfdBean.getFetcherMaster().getScriptName());
			logger.info("FetcherKafkaDetail object constructed successfully.");
	
			logger.info("Fetching Schema for FetcherKafka record.");
			Schema schema = ReflectData.get().getSchema(FetcherKafkaDetail.class);
			logger.info("Schema for FetcherKafka record is: " + schema.toString());
	
			logger.info("Constructing TriggerFetcher response object.");
			List<Records> record = new ArrayList<>();
			record.add(new Records(fetcherKafkaDetailObj));
	
			triggerFetcherResponseModel.setValue_schema(schema.toString());
			triggerFetcherResponseModel.setRecords(record);
			logger.info("TriggerFetcher response object constructed successfully.");
			
		} catch (TransactionSystemException e) {
			
			logger.error(e.getOriginalException());
			throw new Exception(e);
		}

		return triggerFetcherResponseModel;
	}

	/* Added by Kartik */
	public String getCurrentTimestamp() {
		Date dNow = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(dNow);
	}

	/* Added by Kartik */
	public void addTestRunData(Long jobId, String sampleData) throws Exception {
		logger.info("DataService for inserting test job data in ServiceSampleData has been intiated.");
		JobStatusModel jobCheck = new JobStatusModel();
		jobCheck.setJobId(jobId);
		if(dataService.getOneJobStatus(jobCheck)!=null){
			dataService.insertTestData(jobId, sampleData);
		}else{
			throw new DBException("Invalid Job ID Passed for Test Run Data Injestion");
		}
		logger.info("DataService for inserting test job data in ServiceSampleData has been completed successfully.");
	}

	public ServiceParserDetailResponsePojo getParserDetails(String itemid) throws Exception {

		ServiceParserDetailResponsePojo respBean=new ServiceParserDetailResponsePojo();
		ParserDetailResponsePojo parserDetail=new ParserDetailResponsePojo();
		
		JobStatusModel jobStatusBean = new JobStatusModel();
		jobStatusBean.setJobId(Long.parseLong(itemid));
		jobStatusBean = dataService.getOneJobStatus(jobStatusBean);
		
		ServiceMaster serviceMaster=new ServiceMaster();
		serviceMaster.setServiceId(jobStatusBean.getServiceId());
		
		ServiceParserDetail serviceParserDetailBean = new ServiceParserDetail();
		serviceParserDetailBean.setServiceMaster(serviceMaster);
		serviceParserDetailBean = dataService.getParserDetails(serviceMaster);
		
		parserDetail.setParserId(serviceParserDetailBean.getParserMaster().getParserId());
		parserDetail.setParserName(serviceParserDetailBean.getParserMaster().getParserName());
		parserDetail.setScriptName(serviceParserDetailBean.getParserMaster().getScriptName());
		parserDetail.setParserCommand(serviceParserDetailBean.getParserMaster().getParserCommand());
		
		respBean.setMapperId(serviceParserDetailBean.getMapperId());
		respBean.setServiceId(serviceParserDetailBean.getServiceMaster().getServiceId());
		respBean.setParserDetail(parserDetail);
		respBean.setParserArgs(serviceParserDetailBean.getServiceParserArgs());
		respBean.setIsTestJob(jobStatusBean.getIsTestJob());
		respBean.setIsConcatenatedData(dataService.getService(jobStatusBean.getServiceId()).getIsConcatenatedData());
		
		return respBean;
	}
}
