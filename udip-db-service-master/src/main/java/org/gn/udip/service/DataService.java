package org.gn.udip.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.model.CategoryModel;
import org.gn.udip.model.FetcherMaster;
import org.gn.udip.model.ServiceParserDetail;
import org.gn.udip.model.ServiceParserDetailPojo;
import org.gn.udip.model.JobStatusEnum;
import org.gn.udip.model.JobStatusModel;
import org.gn.udip.model.ParserMaster;
import org.gn.udip.model.ParserMasterPojo;
import org.gn.udip.model.ServiceFetcherDetail;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.model.ServiceMasterHistory;
import org.gn.udip.model.ServiceMasterPojo;
import org.gn.udip.model.ServiceNotification;
import org.gn.udip.model.ServiceSampleData;
import org.gn.udip.model.ServiceScheduleMaster;
import org.gn.udip.model.UserNotification;
import org.gn.udip.repository.CategoryRepo;
import org.gn.udip.repository.FetcherRepo;
import org.gn.udip.repository.ServiceParserDetailRepo;
import org.gn.udip.repository.JobStatusRepo;
import org.gn.udip.repository.ServiceNotificationRepo;
import org.gn.udip.repository.ParserMasterRepo;
import org.gn.udip.repository.ServiceFetcherDetailRepo;
import org.gn.udip.repository.ServiceHistoryRepo;
import org.gn.udip.repository.ServiceRepo;
import org.gn.udip.repository.ServiceSampleDataRepo;
import org.gn.udip.repository.ServiceScheduleRepo;
import org.gn.udip.repository.UserNotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataService {

	@Autowired
	JobStatusRepo jobStatusRepo;

	@Autowired
	ServiceRepo serviceRepo;

	@Autowired
	CategoryRepo categoryRepo;

	@Autowired
	ServiceNotificationRepo serviceNotificationRepo;

	@Autowired
	ServiceFetcherDetailRepo serviceFetcherDetailRepo;

	@Autowired
	ServiceParserDetailRepo serviceParserDetailRepo;

	@Autowired
	ServiceSampleDataRepo serviceSampleDataRepo;

	@Autowired
	ParserMasterRepo parserMasterRepo;

	@Autowired
	ServiceScheduleRepo serviceScheduleRepo;

	@Autowired
	FetcherRepo fetcherRepo;

	@Autowired
	ServiceHistoryRepo serviceHistoryRepo;
	
	@Autowired
	UserNotificationRepo userNotificationRepo; 

	private static Logger logger = Logger.getLogger(DataService.class);
	
	public UserNotification addupdateUserNotification(UserNotification bean) throws DBException {
		try {
			bean = userNotificationRepo.saveAndFlush(bean);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return bean;
	}

	public void insertServiceHistory(ServiceMasterHistory bean) throws DBException {
		try {
			serviceHistoryRepo.saveAndFlush(bean);
		} catch (Exception e) {
			throw new DBException(e);
		}
	}

	public ServiceScheduleMaster getServiceScheduleMaster(Long serviceId) throws DBException {
		return serviceScheduleRepo.findByServiceId(serviceId);
	}

	public ServiceScheduleMaster mergeServiceScheduleMaster(ServiceScheduleMaster bean) throws DBException {
		try {
			bean = serviceScheduleRepo.saveAndFlush(bean);
		} catch (Exception e) {
			throw new DBException(e);
		}
		return bean;
	}

	public ServiceFetcherDetail mergeServiceFetcherMapper(ServiceFetcherDetail bean) throws DBException {

		try {

			return serviceFetcherDetailRepo.saveAndFlush(bean);

		}  catch (Exception e) {
			throw new DBException(e);
		}

	}

	public FetcherMaster updateFetcherMaster(FetcherMaster bean) throws Exception {
		FetcherMaster dbBean = null;
		try {

			dbBean = fetcherRepo.findOne(bean.getFetcherId());
			if (dbBean == null)
				throw new DBException("404", "Invalid Fetcher Id");

			dbBean.setFetcherName(bean.getFetcherName() != null ? bean.getFetcherName() : dbBean.getFetcherName());
			dbBean.setFetcherType(bean.getFetcherType() != 0 ? bean.getFetcherType() : dbBean.getFetcherType());
			dbBean.setIsActive(bean.getIsActive() != null ? bean.getIsActive() : dbBean.getIsActive());
			dbBean.setFetcherCommand(
					bean.getFetcherCommand() != null ? bean.getFetcherCommand() : dbBean.getFetcherCommand());
			dbBean.setMandatoryArgs(bean.getMandatoryArgs() != null ? bean.getMandatoryArgs().toString()
					: dbBean.getMandatoryArgs().toString());
			dbBean.setModifiedBy(bean.getModifiedBy() != null ? bean.getModifiedBy() : dbBean.getModifiedBy());
			dbBean.setModifiedOn(Calendar.getInstance().getTime());
			dbBean.setOptionalArgs(bean.getOptionalArgs() != null ? bean.getOptionalArgs().toString()
					: dbBean.getOptionalArgs().toString());
			dbBean.setScriptName(bean.getScriptName() != null ? bean.getScriptName() : dbBean.getScriptName());

			fetcherRepo.saveAndFlush(dbBean);
		} catch (DBException e) {
			// logger.error(e);
			throw e;
		} catch (Exception e) {
			// logger.error(e);
			throw new DBException(e.getMessage());
		}
		logger.info("DataService updateFetcherDetails : " + bean.getFetcherName());
		return bean;
	}

	public FetcherMaster createFetcherMaster(FetcherMaster bean) throws Exception {

		try {

			bean.setCreatedOn(Calendar.getInstance().getTime());
			bean.setModifiedOn(Calendar.getInstance().getTime());
			fetcherRepo.saveAndFlush(bean);
		} catch (Exception e) {
			// logger.error(e);
			throw new DBException(e.getMessage());
		}
		logger.info("DataService createFetcher : " + bean.getFetcherName());
		return bean;
	}

	public ServiceParserDetail getParserDetails(ServiceMaster serviceMaster) throws Exception {

		ServiceParserDetail bean;
		try {
			bean = serviceParserDetailRepo.findByServiceMaster(serviceMaster);
		} catch (Exception e) {
			// logger.error(e);
			throw new DBException(e.getMessage());
		}
		logger.info("DataService getParserDetails for ServiceId : " + bean.getServiceMaster().getServiceId());
		return bean;
	}

	public JobStatusModel getOneJobStatus(JobStatusModel bean) throws Exception {
		try {
			bean = jobStatusRepo.findOne(bean.getJobId());
			if (bean == null)
				throw new DBException("404", "Invalid Job Id");
		} catch (DBException e) {
			// logger.error(e);
			throw e;
		} catch (Exception e) {
			// logger.error(e);
			throw new DBException(e.getMessage());
		}
		logger.info("DataService getOneJobStatus : ID : " + bean.getJobId());
		return bean;
	}

	public JobStatusModel updateOneJobStatus(JobStatusModel bean) throws DBException {

		try {
			JobStatusModel dbBean = jobStatusRepo.findOne(bean.getJobId());

			if (dbBean == null)
				throw new DBException("404", "Invalid Job Id");
			dbBean.setServiceId(bean.getServiceId() != null ? bean.getServiceId() : dbBean.getServiceId());
			dbBean.setFetchedFileCount(
					bean.getFetchedFileCount() != null ? bean.getFetchedFileCount() : dbBean.getFetchedFileCount());
			dbBean.setFetchedFileNames(
					bean.getFetchedFileNames() != null ? bean.getFetchedFileNames() : dbBean.getFetchedFileNames());
			dbBean.setFetcherStartTime(
					bean.getFetcherStartTime() != null ? bean.getFetcherStartTime() : dbBean.getFetcherStartTime());
			dbBean.setFetcherEndTime(
					bean.getFetcherEndTime() != null ? bean.getFetcherEndTime() : dbBean.getFetcherEndTime());
			dbBean.setParserStartTime(
					bean.getParserStartTime() != null ? bean.getParserStartTime() : dbBean.getParserStartTime());
			dbBean.setParserEndTime(
					bean.getParserEndTime() != null ? bean.getParserEndTime() : dbBean.getParserEndTime());
			// dbBean.setFetcherStartedBy(bean.getFetcherStartedBy()!=null?bean.getFetcherStartedBy():dbBean.getFetcherStartedBy());*/
			dbBean.setMessageCount(bean.getMessageCount() != null ? bean.getMessageCount() : dbBean.getMessageCount());
			/*
			 * dbBean.setModifiedBy(bean.getModifiedBy()!=null?bean.
			 * getModifiedBy():dbBean.getModifiedBy());
			 */
			dbBean.setStackTrace(bean.getStackTrace() != null ? bean.getStackTrace() : dbBean.getStackTrace());
			/*
			 * dbBean.setParserStartedBy(bean.getParserStartedBy()!=null?bean.
			 * getParserStartedBy():dbBean.getParserStartedBy());
			 */
			dbBean.setServiceJobStatus(
					bean.getServiceJobStatus() != null ? bean.getServiceJobStatus() : dbBean.getServiceJobStatus());
			dbBean.setBlobRefId(bean.getBlobRefId() != null ? bean.getBlobRefId() : dbBean.getBlobRefId());
			dbBean.setBlobRefUrl(bean.getBlobRefUrl() != null ? bean.getBlobRefUrl() : dbBean.getBlobRefUrl());
			dbBean.setModifiedOn(new Date());

			bean = jobStatusRepo.save(dbBean);

		} catch (DBException e) {
			// logger.error(e);
			throw e;
		} catch (Exception e) {
			// logger.error(e);
			throw new DBException(e.getMessage());
		}

		logger.info("DataService updateOneJobStatus : ID : " + bean.getJobId());
		return bean;
	}

	public List<CategoryModel> getCategory() throws Exception {
		List<CategoryModel> categoryList = null;
		try {
			categoryList = categoryRepo.findAll();
			if (categoryList.size() == 0)
				logger.error("DataService getCategory : No categories found");

		} catch (Exception e) {
			// logger.error(e);
			throw new DBException(e.getMessage());
		}

		return categoryList;
	}

	public ServiceNotification getNotificationConfig(Long serviceId) throws DBException {

		ServiceNotification bean = null;

		try {
			bean = serviceNotificationRepo.findByServiceId(serviceId);
			if(bean != null)
				logger.info("DataService getNotificationConfig : " + bean.getServiceId());
			
		} catch (Exception e) {
			logger.error(e);
			throw new DBException(e.getMessage());
		}

		return bean;
	}

	/* Added by Kartik */
	public ServiceMaster getService(Long serviceId) throws DBException {
		ServiceMaster bean = null;
		try {
			bean = serviceRepo.findOne(serviceId);
			if (bean == null)
				throw new DBException("404", "Invalid Service Id");

		} catch (DBException e) {
			throw e;
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
		logger.info("DataService getJobUniqueId : ServiceId : " + bean.getServiceId() + " | JobUniqueId  : "
				+ bean.getServiceName());

		return bean;
	}

	/* Added by Kartik */
	public JobStatusModel createJob(JobStatusModel jobStatusInsertReq, String jobUniqueId, Long serviceId, Long userId,
			Boolean isTestJob) throws Exception {

		JobStatusModel bean = null;
		try {
			jobStatusInsertReq.setUniqueJobId(jobUniqueId);
			jobStatusInsertReq.setServiceId(serviceId);
			jobStatusInsertReq.setFetcherStartedBy(String.valueOf(userId));
			jobStatusInsertReq.setParserStartedBy(String.valueOf(userId));
			jobStatusInsertReq.setCronStartTime(new Date());
			jobStatusInsertReq.setFetcherStartTime(new Date());
			jobStatusInsertReq.setServiceJobStatus(JobStatusEnum.FETCHER_INITIALIZED);
			jobStatusInsertReq.setIsTestJob(isTestJob);
			jobStatusInsertReq.setCreatedOn(new Date());

			bean = jobStatusRepo.save(jobStatusInsertReq);

		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}

		logger.info(
				"DataService insertJobStatus : JobID : " + bean.getJobId() + " Status :" + bean.getServiceJobStatus());
		return bean;
	}

	/* Added by Kartik */
	public void updateFetcherRunStatus(Long serviceId) throws DBException {
		ServiceMaster bean;
		try {
			bean = serviceRepo.findOne(serviceId);

			if (bean == null)
				throw new DBException("404", "Invalid Service Id");

			bean.setFetcherRunStatus(JobStatusEnum.FETCHER_INITIALIZED);

			bean = serviceRepo.save(bean);

		} catch (DBException e) {
			throw e;
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}

		logger.info("DataService updateFetcherRunStatus : ServiceId : " + bean.getServiceId() + " to "
				+ bean.getFetcherRunStatus());
	}

	/* Added by Kartik */
	public ServiceFetcherDetail getServiceFetcherDetails(ServiceMaster serviceMaster) throws DBException {
		ServiceFetcherDetail bean = null;
		try {
			bean = serviceFetcherDetailRepo.findByServiceMaster(serviceMaster);
			if (bean != null){
				logger.info("DataService getServiceFetcherDetails : ServiceMasterId : " + bean.getServiceMaster().getServiceId()
						+ " | FetcherMasterId  : " + bean.getFetcherMaster().getFetcherId());
			}
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
		
		return bean;
	}

	/* Added by Kartik */
	public void insertTestData(Long jobId, String sampleData) throws DBException {

		ServiceSampleData sampleDataInsertReq = new ServiceSampleData();
		ServiceSampleData sampleDataInsertResp = null;

		try {

			sampleDataInsertReq.setJobId(jobId);
			sampleDataInsertReq.setSampleData(sampleData);
			sampleDataInsertReq.setCreatedOn(new Date());
			sampleDataInsertResp = serviceSampleDataRepo.save(sampleDataInsertReq);

		} catch (Exception e) {
			throw new DBException("400", "Bad Request - Insertion failed ");

		}

		logger.info("DataService insertTestData : JobId : " + sampleDataInsertResp.getJobId());

	}

	/* Added by Kartik */
	public void truncateTestData(Date expiryDate) throws DBException {
		try {
			List<ServiceSampleData> truncateDataList = serviceSampleDataRepo.findByCreatedOnBefore(expiryDate);
			if (!truncateDataList.isEmpty()) {
				List<Long> truncateJobIdList = new ArrayList<>();
				for (ServiceSampleData sampleData : truncateDataList) {
					truncateJobIdList.add(sampleData.getJobId());
				}
				serviceSampleDataRepo.deleteByCreatedOnBefore(expiryDate);
				jobStatusRepo.deleteByJobIdIn(truncateJobIdList);
				logger.info("Service for truncating test data completed successfully.");
			}
			logger.info("No test data to truncate.");
		} catch (Exception e) {
			throw new DBException("400", "Exception occured while truncating test data from database.");
		}

	}

	/* Added by Kartik */
	public void insertParserMaster(ParserMasterPojo parserMasterJson) throws DBException {

		ParserMaster savedParserData;
		try {
			savedParserData = parserMasterRepo.save(setAndGetParserMasterBean(parserMasterJson, new ParserMaster(),true));

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of ParserData in database");
			throw new DBException("400", "Bad Request - Insertion failed ");
		}

		logger.info(
				"Insertion of ParserData in database was successful with ParserId : " + savedParserData.getParserId());
	}

	/* Added by Kartik */
	public void updateParserMaster(ParserMasterPojo parserMasterJson) throws DBException {

		ParserMaster parserMasterBean;
		try {
			parserMasterBean = parserMasterRepo.findOne(parserMasterJson.getParserId());
			if (parserMasterBean == null)
				throw new DBException("404", "Invalid Parser Id");

			parserMasterRepo.save(setAndGetParserMasterBean(parserMasterJson, parserMasterBean,false));

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ParserData in database");
			throw new DBException("400", "Bad Request - Update failed ");
		}

		logger.info(
				"Update of ParserData in database was successful with ParserId : " + parserMasterBean.getParserId());
	}
	
	/* Added by Kartik */
	private ParserMaster setAndGetParserMasterBean(ParserMasterPojo parserMasterJson,ParserMaster parserMasterBean, boolean isInsert) {
		
		parserMasterBean.setParserName(parserMasterJson.getParserName());
		if (parserMasterJson.getMandatoryArgs() != null && parserMasterJson.getMandatoryArgs().size()>0)
			parserMasterBean.setMandatoryArgs(parserMasterJson.getMandatoryArgs().toString());
		else
			parserMasterBean.setMandatoryArgs("");
		if (parserMasterJson.getOptionalArgs() != null && parserMasterJson.getOptionalArgs().size()>0)
			parserMasterBean.setOptionalArgs(parserMasterJson.getOptionalArgs().toString());
		else
			parserMasterBean.setOptionalArgs("");
		parserMasterBean.setScriptName(parserMasterJson.getScriptName());
		parserMasterBean.setParserCommand(parserMasterJson.getParserCommand());
		if(isInsert){
			parserMasterBean.setCreatedBy(parserMasterJson.getCreatedBy());
			parserMasterBean.setModifiedBy(parserMasterJson.getCreatedBy());
		}
		else
			parserMasterBean.setModifiedBy(parserMasterJson.getModifiedBy());
		
		return parserMasterBean;
	}

	/* Added by Kartik */
	public void deleteParserMaster(ParserMasterPojo parserDataReq) throws DBException {

		ParserMaster parserMasterBean;
		try {
			parserMasterBean = parserMasterRepo.findOne(parserDataReq.getParserId());
			if (parserMasterBean == null)
				throw new DBException("404", "Invalid Parser Id");

			parserMasterBean.setIsActive("0");
			parserMasterBean.setModifiedBy(parserDataReq.getModifiedBy());

			parserMasterRepo.save(parserMasterBean);

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in delete of ParserData in database");
			throw new DBException("400", "Bad Request - Deletion failed ");
		}

		logger.info(
				"Delete of ParserData in database was successful with ParserId : " + parserMasterBean.getParserId());

	}

	/* Added by Kartik */
	public Long insertServiceMaster(ServiceMasterPojo serviceDataReq) throws DBException {

		ServiceMaster serviceMasterBean = new ServiceMaster();
		ServiceMaster savedServiceData;
		try {

			serviceMasterBean.setServiceName(serviceDataReq.getServiceName());
			serviceMasterBean.setUniqueId(serviceDataReq.getUniqueId());
			serviceMasterBean.setCreatedBy(serviceDataReq.getCreatedBy());
			serviceMasterBean.setModifiedBy(serviceDataReq.getCreatedBy());

			savedServiceData = serviceRepo.save(serviceMasterBean);

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of ServiceData in database");
			throw new DBException("400", "Bad Request - Insertion failed ");

		}

		logger.info("Insertion of ServiceData in database was successful with ServiceId : "
				+ savedServiceData.getServiceId());
		
		return savedServiceData.getServiceId();

	}

	/* Added by Kartik */
	public void updateServiceMaster(ServiceMasterPojo serviceDataReq, CategoryModel categoryModel, boolean isUpdateServiceName, boolean isUpdateSchedulable) throws DBException {

		ServiceMaster serviceMasterBean;
		try {
			serviceMasterBean = serviceRepo.findOne(serviceDataReq.getServiceId());
			if (serviceMasterBean == null)
				throw new DBException("404", "Invalid Service Id");

			if (categoryModel != null) {
				serviceMasterBean.setVertical(serviceDataReq.getVertical());
				serviceMasterBean.setCategory(categoryModel);
				serviceMasterBean.setOwner(serviceDataReq.getOwner());
				serviceMasterBean.setStatus(serviceDataReq.getStatus());
				serviceMasterBean.setCharset(serviceDataReq.getCharset());
				serviceMasterBean.setIsSchedulable(serviceDataReq.getIsSchedulable());
				serviceMasterBean.setIsConcatenatedData(serviceDataReq.getIsConcatenatedData().equals("1")?true:false);
				serviceMasterBean.setModifiedBy(serviceDataReq.getModifiedBy());
			}
			else if(isUpdateServiceName) {
				serviceMasterBean.setServiceName(serviceDataReq.getServiceName());
				serviceMasterBean.setUniqueId(serviceDataReq.getUniqueId());
				serviceMasterBean.setModifiedBy(serviceDataReq.getModifiedBy());
			}
			else if(isUpdateSchedulable) {
				serviceMasterBean.setIsSchedulable(serviceDataReq.getIsSchedulable());
				serviceMasterBean.setModifiedBy(serviceDataReq.getModifiedBy());
			}
			
			serviceRepo.save(serviceMasterBean);

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ServiceData in database");
			throw new DBException("400", "Bad Request - Update failed ");

		}

		logger.info("Update of ServiceData in database was successful with ServiceId : "
				+ serviceMasterBean.getServiceId());

	}

	/* Added by Kartik */
	public void deleteServiceMaster(ServiceMasterPojo serviceDataReq) throws DBException {

		ServiceMaster serviceMasterBean;
		try {
			serviceMasterBean = serviceRepo.findOne(serviceDataReq.getServiceId());
			if (serviceMasterBean == null)
				throw new DBException("404", "Invalid Service Id");

			serviceMasterBean.setIsActive("0");
			serviceMasterBean.setModifiedBy(serviceDataReq.getModifiedBy());

			serviceRepo.save(serviceMasterBean);

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in delete of ServiceData in database");
			throw new DBException("400", "Bad Request - Deletion failed ");
		}

		logger.info("Delete of ServiceData in database was successful with ServiceId : "
				+ serviceMasterBean.getServiceId());

	}

	/* Added by Kartik */
	public void insertupdateServiceParser(ServiceParserDetailPojo serviceParserJson, ServiceMaster serviceMaster, ParserMaster parserMaster)
			throws DBException {

		ServiceParserDetail serviceParserBean;
		ServiceParserDetail savedServiceParserData;
		try {

			serviceParserBean = serviceParserDetailRepo.findByServiceMaster(serviceMaster);
			if (serviceParserBean == null){
				logger.info("Insertion of ServiceParserData in database initiated");
				serviceParserBean=new ServiceParserDetail();
				serviceParserBean.setServiceMaster(serviceMaster);
				serviceParserBean.setParserMaster(parserMaster);
				if(serviceParserJson.getServiceParserArgs()!=null && !serviceParserJson.getServiceParserArgs().isJsonNull())
					serviceParserBean.setServiceParserArgs(serviceParserJson.getServiceParserArgs().toString());
				serviceParserBean.setCreatedBy(serviceParserJson.getCreatedBy());
				serviceParserBean.setModifiedBy(serviceParserJson.getCreatedBy());
			}
			else {
				logger.info("Update of ServiceParserData in database initiated");
				serviceParserBean.setParserMaster(parserMaster);
				if(serviceParserJson.getServiceParserArgs()!=null && !serviceParserJson.getServiceParserArgs().isJsonNull())
					serviceParserBean.setServiceParserArgs(serviceParserJson.getServiceParserArgs().toString());
				else
					serviceParserBean.setServiceParserArgs(null);
				serviceParserBean.setModifiedBy(serviceParserJson.getModifiedBy());
			}
			
			savedServiceParserData = serviceParserDetailRepo.save(serviceParserBean);

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion/update of ServiceParserData in database");
			throw new DBException("400", "Bad Request - Insertion/Update failed ");

		}

		logger.info("Insertion/Update of ServiceParserData in database was successful with Id : "
				+ savedServiceParserData.getMapperId());
	}

	/* Added by Kartik */
	public void insertupdateServiceNotification(ServiceNotification bean) throws DBException {
		ServiceNotification updatedbean;
		try {
			updatedbean = serviceNotificationRepo.save(bean);

		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion/update of ServiceNotificationData in database");
			throw new DBException("400", "Bad Request - Insertion/Update failed ");

		}
		logger.info("Insertion/Update of ServiceNotificationData in database was successful with ServiceId : "
				+ updatedbean.getServiceId());
	}

}
