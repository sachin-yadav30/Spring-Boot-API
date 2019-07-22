package org.gn.udip.service;

import org.apache.log4j.Logger;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.CategoryModel;
import org.gn.udip.model.ServiceMasterPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceMasterService {

	@Autowired
	DataService dataService;
	
	private static Logger logger = Logger.getLogger(ServiceMasterService.class);
	
	public Long addServiceData(ServiceMasterPojo serviceDataReq) throws ValidationException, DBException {

		Long serviceId;
		try {
			validateData(serviceDataReq, false);
			serviceId=dataService.insertServiceMaster(serviceDataReq);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in insertion of ServiceData");
			throw new ValidationException("422", "Validation Failure - Insertion failed ");
		}
		return serviceId;
	}
	
	public void updateServiceData(ServiceMasterPojo serviceDataReq, boolean isUpdateServiceName, boolean isUpdateSchedulable) throws ValidationException, DBException {

		try 
		{
			if(serviceDataReq.getServiceId()==null)
				throw new ValidationException("ServiceId is Null, expected to provide ServiceId");
			if(!nullAndEmptyCheck(serviceDataReq.getModifiedBy()))
				throw new ValidationException("ModifiedBy is Null or Empty, expected to provide ModifiedBy");
			
			if(isUpdateServiceName)
			{		
				if(!nullAndEmptyCheck(serviceDataReq.getServiceName()))
					throw new ValidationException("ServiceName is Null or Empty, expected to provide ServiceName");
				if(!nullAndEmptyCheck(serviceDataReq.getUniqueId()))
					throw new ValidationException("UniqueId is Null or Empty, expected to provide UniqueId");
				
				dataService.updateServiceMaster(serviceDataReq,null,true,false);
			}
			else if(isUpdateSchedulable)
			{
				if(!nullAndEmptyCheck(serviceDataReq.getIsSchedulable()))
					throw new ValidationException("IsSchedulable is Null or Empty, expected to provide IsSchedulable");

				dataService.updateServiceMaster(serviceDataReq,null,false,true);
			}
			else
			{
				CategoryModel categoryModel=new CategoryModel();
				validateData(serviceDataReq, true);
				categoryModel.setCategoryId(Long.parseLong(serviceDataReq.getCategory()));
				dataService.updateServiceMaster(serviceDataReq,categoryModel,false,false);
			}
			
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ServiceData");
			throw new ValidationException("422", "Validation Failure - Update failed ");
		}
		catch (Exception e) {
			logger.error(e.getMessage());
			logger.error("Failure in update of ServiceData");
			throw new DBException("400", "Bad Request - Update failed ");
			
		}
		
	}
	
	public void deleteServiceData(ServiceMasterPojo serviceDataReq) throws DBException, ValidationException
	{
		try {
			if(serviceDataReq.getServiceId()==null)
				throw new ValidationException("ServiceId is Null, expected to provide ServiceId");
			if(!nullAndEmptyCheck(serviceDataReq.getModifiedBy()))
				throw new ValidationException("ModifiedBy is Null or Empty, expected to provide ModifiedBy");
			
			dataService.deleteServiceMaster(serviceDataReq);
		}
		catch(ValidationException e) {
			logger.error(e.getMessage());
			logger.error("Failure in delete of ServiceData");
			throw new ValidationException("422", "Validation Failure - Deletion failed ");
		}
	}
	
	private void validateData(ServiceMasterPojo serviceDataReq, boolean isUpdate) throws ValidationException
	{
		if(!isUpdate && !nullAndEmptyCheck(serviceDataReq.getServiceName()))
			throw new ValidationException("ServiceName is Null or Empty, expected to provide ServiceName");
		
		if(!isUpdate && !nullAndEmptyCheck(serviceDataReq.getUniqueId()))
			throw new ValidationException("UniqueId is Null or Empty, expected to provide UniqueId");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getCategory()))
			throw new ValidationException("CategoryId is Null or Empty, expected to provide CategoryId");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getVertical()))
			throw new ValidationException("Vertical is Null or Empty, expected to provide Vertical");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getOwner()))
			throw new ValidationException("Owner is Null or Empty, expected to provide Owner");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getStatus()))
			throw new ValidationException("Status is Null or Empty, expected to provide Status");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getCharset()))
			throw new ValidationException("Charset is Null or Empty, expected to provide Charset");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getIsSchedulable()))
			throw new ValidationException("IsSchedulable is Null or Empty, expected to provide IsSchedulable");
		
		if(isUpdate && !nullAndEmptyCheck(serviceDataReq.getIsConcatenatedData()))
			throw new ValidationException("IsConcatenatedData is Null or Empty, expected to provide IsConcatenatedData");
		
		if(!isUpdate && !nullAndEmptyCheck(serviceDataReq.getCreatedBy()))
			throw new ValidationException("CreatedBy is Null or Empty, expected to provide CreatedBy");
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
