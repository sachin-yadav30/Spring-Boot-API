package org.gn.udip.service;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.ParserMaster;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.model.ServiceParserDetailPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

@Service
public class ServiceParserService {
	
	@Autowired
	DataService dataService;
	
	private static Logger logger = Logger.getLogger(ServiceParserService.class);

	public void addupdateServiceParserData(String serviceParserDataReq) throws ValidationException, DBException {
		
		Gson gson = new Gson();	
		ServiceMaster serviceMaster=new ServiceMaster();
		ParserMaster parserMaster=new ParserMaster();
		try {
			ServiceParserDetailPojo serviceParserJson=gson.fromJson(serviceParserDataReq, ServiceParserDetailPojo.class);
			validateData(serviceParserJson);
			serviceMaster.setServiceId(serviceParserJson.getServiceId());
			parserMaster.setParserId(serviceParserJson.getParserId());
			dataService.insertupdateServiceParser(serviceParserJson,serviceMaster,parserMaster);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion/update of ServiceParserData");
			throw new ValidationException("422", "Validation Failure - Insertion/Update failed ");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion/update of ServiceParserData");
			throw new DBException("400", "Bad Request - Insertion/Update failed ");
			
		}
	}
	
	private void validateData(ServiceParserDetailPojo serviceParserJson) throws ValidationException
	{
		if(serviceParserJson.getServiceId()==null)
			throw new ValidationException("ServiceId is Null, expected to provide ServiceId");
		
		if(serviceParserJson.getParserId()==null)
			throw new ValidationException("ParserId is Null, expected to provide ParserId");
	}
}
