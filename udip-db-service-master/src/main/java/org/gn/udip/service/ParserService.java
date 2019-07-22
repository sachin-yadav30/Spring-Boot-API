package org.gn.udip.service;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.ParserMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ParserService {
	
	@Autowired
	DataService dataService;
	
	private static Logger logger = Logger.getLogger(ParserService.class);
	
	public void addParserData(String parserDataReq) throws DBException, ValidationException
	{
		Gson gson = new Gson();	
		try {
			ParserMasterPojo parserMasterJson=gson.fromJson(parserDataReq, ParserMasterPojo.class);
			validateData(parserMasterJson, false);
			dataService.insertParserMaster(parserMasterJson);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of ParserData");
			throw new ValidationException("422", "Validation Failure - Insertion failed ");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of ParserData");
			throw new DBException("400", "Bad Request - Insertion failed ");
			
		}
		
	}
	
	
	public void updateParserData(String parserDataReq) throws DBException, ValidationException
	{
		Gson gson = new Gson();
		try {
			ParserMasterPojo parserMasterJson=gson.fromJson(parserDataReq, ParserMasterPojo.class);
			validateData(parserMasterJson, true);
			dataService.updateParserMaster(parserMasterJson);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ParserData");
			throw new ValidationException("422", "Validation Failure - Update failed ");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ParserData");
			throw new DBException("400", "Bad Request - Update failed ");
			
		}
	}
	
	public void deleteParserData(ParserMasterPojo parserDataReq) throws DBException, ValidationException
	{
		try {
			if(parserDataReq.getParserId()==null)
				throw new ValidationException("ParserId is Null, expected to provide ParserId");
			if(!nullAndEmptyCheck(parserDataReq.getModifiedBy()))
				throw new ValidationException("ModifiedBy is Null or Empty, expected to provide ModifiedBy");
			
			dataService.deleteParserMaster(parserDataReq);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in delete of ParserData");
			throw new ValidationException("422", "Validation Failure - Deletion failed ");
		}
	}
	
	private void validateData(ParserMasterPojo parserMasterJson, boolean isUpdate) throws ValidationException
	{
		if(isUpdate && (parserMasterJson.getParserId()==null))
			throw new ValidationException("ParserId is Null, expected to provide ParserId");
	
		if(!nullAndEmptyCheck(parserMasterJson.getParserName()))
			throw new ValidationException("ParserName is Null or Empty, expected to provide ParserName");
		
		if(!nullAndEmptyCheck(parserMasterJson.getScriptName()))
			throw new ValidationException("ScriptName is Null or Empty, expected to provide ScriptName");
		
		if(!nullAndEmptyCheck(parserMasterJson.getParserCommand()))
			throw new ValidationException("ParserCommand is Null or Empty, expected to provide ParserCommand");
		
		if(!isUpdate && !nullAndEmptyCheck(parserMasterJson.getCreatedBy()))
			throw new ValidationException("CreatedBy is Null or Empty, expected to provide CreatedBy");
		
		if(isUpdate && !nullAndEmptyCheck(parserMasterJson.getModifiedBy()))
			throw new ValidationException("ModifiedBy is Null or Empty, expected to provide ModifiedBy");
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


