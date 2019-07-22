package org.gn.udip.service;

import java.util.Calendar;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.FetcherMaster;
import org.gn.udip.model.FetcherMasterReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class FetcherService {

	@Autowired
	DataService dataService;

	Gson gson = new Gson();

	private static Logger logger = Logger.getLogger(FetcherService.class);
	
	
	public void addFetcher(String requestJsonString) throws DBException, ValidationException {
		FetcherMasterReq fetcherReq = null;

		try {
			fetcherReq = gson.fromJson(requestJsonString, FetcherMasterReq.class);
			validateData(fetcherReq, false);
			FetcherMaster bean = new FetcherMaster();
			bean.setCreatedBy(fetcherReq.getCreatedBy() != null ? fetcherReq.getCreatedBy() : null);
			bean.setCreatedOn(Calendar.getInstance().getTime());
			bean.setFetcherCommand(fetcherReq.getFetcherCommand() != null ? fetcherReq.getFetcherCommand() : null);
			bean.setFetcherName(fetcherReq.getFetcherName() != null ? fetcherReq.getFetcherName() : null);
			bean.setFetcherType(fetcherReq.getFetcherType() != 0 ? fetcherReq.getFetcherType() : 0);
			bean.setIsActive(fetcherReq.getIsActive() != null ? fetcherReq.getIsActive() : "1");
			bean.setMandatoryArgs(fetcherReq.getMandatoryArgs() != null && fetcherReq.getMandatoryArgs().size() > 0 ? fetcherReq.getMandatoryArgs().toString(): "");
			bean.setModifiedOn(Calendar.getInstance().getTime());
			bean.setOptionalArgs(fetcherReq.getOptionalArgs() != null && fetcherReq.getOptionalArgs().size() > 0 ? fetcherReq.getOptionalArgs().toString() : "");
			bean.setScriptName(fetcherReq.getScriptName() != null ? fetcherReq.getScriptName() : null);

			dataService.createFetcherMaster(bean);
		} catch (ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of Fetcher Master");
			throw new ValidationException("422", "Validation Failure - Insertion failed ");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of Fetcher Master");
			throw new DBException("400", "Bad Request - Insertion failed ");

		}
		logger.info("Fetcher Created with Name "+fetcherReq.getFetcherName());
	}

	public void updateFetcherMaster(String reqJsonString) throws DBException, ValidationException {
		FetcherMasterReq fetcherReq = null;

		FetcherMaster bean = new FetcherMaster();
		try {
			fetcherReq = gson.fromJson(reqJsonString, FetcherMasterReq.class);
			validateData(fetcherReq, true);
			bean.setFetcherId(fetcherReq.getFetcherId());
			bean.setFetcherCommand(fetcherReq.getFetcherCommand() != null ? fetcherReq.getFetcherCommand() : null);
			bean.setFetcherName(fetcherReq.getFetcherName() != null ? fetcherReq.getFetcherName() : null);
			bean.setFetcherType(fetcherReq.getFetcherType() != 0 ? fetcherReq.getFetcherType() : 0);
			bean.setIsActive(fetcherReq.getIsActive() != null ? fetcherReq.getIsActive() : "1");
			bean.setMandatoryArgs(fetcherReq.getMandatoryArgs() != null && fetcherReq.getMandatoryArgs().size() > 0 ? fetcherReq.getMandatoryArgs().toString() : "");
			bean.setModifiedOn(Calendar.getInstance().getTime());
			bean.setOptionalArgs(fetcherReq.getOptionalArgs() != null && fetcherReq.getOptionalArgs().size() > 0 ? fetcherReq.getOptionalArgs().toString() : "");
			bean.setScriptName(fetcherReq.getScriptName() != null ? fetcherReq.getScriptName() : null);
			dataService.updateFetcherMaster(bean);
		} catch (ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of Fetcher Master");
			throw new ValidationException("422", "Validation Failure - Update failed ");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ParserData");
			throw new DBException("400", "Bad Request - Update failed ");

		}
		logger.info("Fetcher Updated with Name "+fetcherReq.getFetcherName());
	}

	public void deleteFetcher(String reqJsonString) throws DBException, ValidationException {
		FetcherMasterReq fetcherReq = null;
		FetcherMaster bean = new FetcherMaster();
		try {
			fetcherReq = gson.fromJson(reqJsonString, FetcherMasterReq.class);
			if (fetcherReq.getFetcherId() == null)
				throw new ValidationException("Fetcher Id is Null");
			if (fetcherReq.getModifiedBy() == null || fetcherReq.getModifiedBy().isEmpty())
				throw new ValidationException("ModifiedBy is Null or Empty");
			bean.setFetcherId(fetcherReq.getFetcherId());
			bean.setModifiedBy(fetcherReq.getModifiedBy());
			bean.setIsActive("0");
			dataService.updateFetcherMaster(bean);
		} catch (ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in delete of ParserData");
			throw new ValidationException("422", "Validation Failure - Deletion failed ");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ParserData");
			throw new DBException("400", "Bad Request - Update failed ");

		}
		
		logger.info("Fetcher Deleted with Name "+fetcherReq.getFetcherName());
	}

	private void validateData(FetcherMasterReq fetcherReq, boolean isUpdate) throws ValidationException {
		if (isUpdate && fetcherReq.getFetcherId() == null)
			throw new ValidationException("Fetcher Id is Null");

		if (fetcherReq.getFetcherName() == null || fetcherReq.getFetcherName().isEmpty())
			throw new ValidationException("Fetcher Name is Null or Empty");

		if (fetcherReq.getScriptName() == null || fetcherReq.getScriptName().isEmpty())
			throw new ValidationException("ScriptName is Null or Empty");

		if (fetcherReq.getFetcherCommand() == null || fetcherReq.getFetcherCommand().isEmpty())
			throw new ValidationException("Fetcher Command is Null or Empty");

		if (!isUpdate && (fetcherReq.getCreatedBy() == null || fetcherReq.getCreatedBy().isEmpty()))
			throw new ValidationException("CreatedBy is Null or Empty");

		if (isUpdate && (fetcherReq.getModifiedBy() == null || fetcherReq.getModifiedBy().isEmpty()))
			throw new ValidationException("ModifiedBy is Null or Empty");
	}

}
