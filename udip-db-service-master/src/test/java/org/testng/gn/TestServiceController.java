package org.testng.gn;

import org.gn.udip.UDIPSQLConnectMain;
import org.gn.udip.controller.ServiceController;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.model.ServiceCreationResponse;
import org.gn.udip.model.ServiceFetcherDetail;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.model.ServiceMasterPojo;
import org.gn.udip.model.ServiceNotification;
import org.gn.udip.model.ServiceNotificationPojo;
import org.gn.udip.model.ServiceParserDetailPojo;
import org.gn.udip.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;


@SpringBootTest(classes = UDIPSQLConnectMain.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@EnableJpaRepositories(basePackages = "org.gn.udip.repository")
public class TestServiceController extends AbstractTestNGSpringContextTests{
	
	@Autowired
	ServiceController serviceController;
	
	@Autowired
	DataService dataService;
	
	@Test
	public void testAddServiceData()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceName("test");
		serviceDataReq.setUniqueId("testid");
		serviceDataReq.setCreatedBy("admin");
		
		ServiceCreationResponse serviceCreationResponse = serviceController.addServiceData(serviceDataReq);
		Assert.assertNotNull(serviceCreationResponse);
		Assert.assertEquals(serviceCreationResponse.getStatusCode(),"00");
		Assert.assertEquals(serviceCreationResponse.getStatusDesc(), "Request Successfuly Processed");
		Assert.assertNotNull(serviceCreationResponse.getServiceId());
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddServiceDataWithServiceNameAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setUniqueId("testid");
		serviceDataReq.setCreatedBy("admin");
		
		serviceController.addServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddServiceDataWithUniqueIdAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceName("test");
		serviceDataReq.setCreatedBy("admin");
		
		serviceController.addServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddServiceDataWithCreatedByAsEmpty()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceName("test");
		serviceDataReq.setUniqueId("testid");
		serviceDataReq.setCreatedBy("");
		
		serviceController.addServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testInsertServiceMasterWithDBException() throws DBException{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		dataService.insertServiceMaster(serviceDataReq);
	}
	
	@Test
	public void testUpdateServiceData()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setVertical("2");
		serviceDataReq.setCategory("3");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		RequestStatus status = serviceController.updateServiceData(serviceDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceDataWithIsConcatinatedDataAsFalse()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setVertical("2");
		serviceDataReq.setCategory("3");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("0");
		serviceDataReq.setModifiedBy("testuser");
		
		RequestStatus status = serviceController.updateServiceData(serviceDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithServiceIdAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setVertical("2");
		serviceDataReq.setCategory("3");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithVerticalAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithCategoryAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setVertical("2");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithOwnerAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setVertical("2");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithStatusAsEmpty()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setVertical("2");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithCharsetAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setVertical("2");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithIsSchedulableAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setVertical("2");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithIsConcatenatedDataAsEmpty()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setVertical("2");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceDataWithModifiedByAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setCategory("3");
		serviceDataReq.setVertical("2");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testUpdateServiceDataWithDBException()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(7));
		serviceDataReq.setVertical("2");
		serviceDataReq.setCategory("3");
		serviceDataReq.setOwner("testowner");
		serviceDataReq.setStatus("0");
		serviceDataReq.setCharset("default");
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setIsConcatenatedData("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceData(serviceDataReq);
	}
	
	@Test
	public void testUpdateServiceNameData()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setServiceName("testupdate");
		serviceDataReq.setUniqueId("testidupdate");
		serviceDataReq.setModifiedBy("testuser");
		
		RequestStatus status = serviceController.updateServiceNameData(serviceDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceNameDataWithServiceIdAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceName("testupdate");
		serviceDataReq.setUniqueId("testidupdate");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceNameData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceNameDataWithServiceNameAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setUniqueId("testidupdate");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceNameData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceNameDataWithUniqueIdAsEmpty()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setServiceName("testupdate");
		serviceDataReq.setUniqueId("");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceNameData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceNameDataWithModifiedByAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setServiceName("testupdate");
		serviceDataReq.setUniqueId("testidupdate");
		
		serviceController.updateServiceNameData(serviceDataReq);
	}
	
	@Test
	public void testUpdateServiceSchedulableData()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setModifiedBy("testuser");
		
		RequestStatus status = serviceController.updateServiceSchedulableData(serviceDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceSchedulableDataWithServiceIdAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setIsSchedulable("1");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceSchedulableData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceSchedulableDataWithIsSchedulableAsEmpty()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setIsSchedulable("");
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.updateServiceSchedulableData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceSchedulableDataWithModifiedByAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setIsSchedulable("1");
		
		serviceController.updateServiceSchedulableData(serviceDataReq);
	}
	
	@Test
	public void testDeleteServiceData()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setModifiedBy("testuser");
		
		RequestStatus status = serviceController.deleteServiceData(serviceDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteServiceDataWithServiceIdAsNull()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.deleteServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteServiceDataWithModifiedByAsEmpty()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(25));
		serviceDataReq.setModifiedBy("");
		
		serviceController.deleteServiceData(serviceDataReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testDeleteServiceDataWithDBException()throws Exception{
		
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceId(Long.valueOf(7));
		serviceDataReq.setModifiedBy("testuser");
		
		serviceController.deleteServiceData(serviceDataReq);
	}
	
	@Test
	public void testAddServiceParserData()throws Exception{
		
		String serviceParserDataReq="{\"serviceId\":25,\"parserId\":13,\"serviceParserArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		RequestStatus status = serviceController.addupdateServiceParserData(serviceParserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testAddServiceParserDataWithServiceParserArgAsNull()throws Exception{
				
		String serviceParserDataReq="{\"serviceId\":25,\"parserId\":13,\"createdBy\":\"admin\"}";
		RequestStatus status = serviceController.addupdateServiceParserData(serviceParserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testAddServiceParserDataWithServiceParserArgAsJsonNull()throws Exception{
				
		String serviceParserDataReq="{\"serviceId\":25,\"parserId\":13,\"serviceParserArgs\":null,\"createdBy\":\"admin\"}";
		RequestStatus status = serviceController.addupdateServiceParserData(serviceParserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceParserData()throws Exception{
		
		String serviceParserDataReq="{\"serviceId\":26,\"parserId\":14,\"serviceParserArgs\":{\"optional\": {\"updathumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"updatbaseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"modifiedBy\":\"updateuser\"}";
		RequestStatus status = serviceController.addupdateServiceParserData(serviceParserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceParserDataWithServiceParserArgAsNull()throws Exception{
		
		String serviceParserDataReq="{\"serviceId\":26,\"parserId\":14,\"modifiedBy\":\"updateuser\"}";
		RequestStatus status = serviceController.addupdateServiceParserData(serviceParserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceParserDataWithServiceParserArgAsJsonNull()throws Exception{
		
		String serviceParserDataReq="{\"serviceId\":26,\"parserId\":14,\"serviceParserArgs\":null,\"modifiedBy\":\"updateuser\"}";
		RequestStatus status = serviceController.addupdateServiceParserData(serviceParserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceParserDataWithServiceIdAsNull()throws Exception{
		
		String serviceParserDataReq="{\"parserId\":35,\"serviceParserArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		serviceController.addupdateServiceParserData(serviceParserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceParserDataWithParserIdAsNull()throws Exception{
		
		String serviceParserDataReq="{\"serviceId\":25,\"serviceParserArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		serviceController.addupdateServiceParserData(serviceParserDataReq);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testAddUpdateServiceParserDataWithInvalidJson()throws Exception{
		
		String serviceParserDataReq="{\"serviceId\":25,\"parserId\":35,\"serviceParserArgs\":{\"optional\": \"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		serviceController.addupdateServiceParserData(serviceParserDataReq);	
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testInsertupdateServiceParserWithDBException() throws DBException{
		
		Gson gson = new Gson();
		String serviceParserDataReq="{\"serviceParserArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		ServiceParserDetailPojo serviceParserJson=gson.fromJson(serviceParserDataReq, ServiceParserDetailPojo.class);
		
		dataService.insertupdateServiceParser(serviceParserJson,null,null);
	}
	
	@Test
	public void testAddServiceNotificationData()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("ALL");
		serviceNotificationDataReq.setReceptionList("abc@nielsen.com,xyz@nielsen.com");
		
		RequestStatus status = serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testAddServiceNotificationDataWithReceptionListAsNull()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("ALL");
		
		RequestStatus status = serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testAddServiceNotificationDataWithReceptionListAsEmpty()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("ALL");
		serviceNotificationDataReq.setReceptionList("");
		
		RequestStatus status = serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceNotificationData()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("ALL");
		serviceNotificationDataReq.setReceptionList("abc@nielsen.com,xyz@nielsen.com");
		
		RequestStatus status = serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceNotificationDataWithReceptionListAsNull()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("ALL");
		
		RequestStatus status = serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateServiceNotificationDataWithReceptionListAsEmpty()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("ALL");
		serviceNotificationDataReq.setReceptionList("");
		
		RequestStatus status = serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceNotificationDataWithServiceIdAsNull()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setNotifyLevel("ALL");
		serviceNotificationDataReq.setReceptionList("abc@nielsen.com,xyz@nielsen.com");
		
		serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);	
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceNotificationDataWithNotifyLevelAsNull()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setReceptionList("abc@nielsen.com,xyz@nielsen.com");
		
		serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);	
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceNotificationDataWithNotifyLevelAsEmpty()throws Exception{
		
		ServiceNotificationPojo serviceNotificationDataReq=new ServiceNotificationPojo();
		serviceNotificationDataReq.setServiceId(Long.valueOf(25));
		serviceNotificationDataReq.setNotifyLevel("");
		serviceNotificationDataReq.setReceptionList("abc@nielsen.com,xyz@nielsen.com");
		
		serviceController.addupdateServiceNotificationData(serviceNotificationDataReq);	
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testinsertupdateServiceNotificationWithDBException() throws DBException{
		
		ServiceNotification bean=new ServiceNotification();
		dataService.insertupdateServiceNotification(bean);
	}
	
	//Service Fetcher Test cases here 
	@Test
	public void testAddServiceFetcherData()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":26,\"fetcherId\":8,\"serviceFetcherArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		RequestStatus status = serviceController.configureServiceFetcher(serviceFetcherDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testAddServiceFetcherDataWithServiceFetcherArgAsNull()throws Exception{
				
		String serviceFetcherDataReq="{\"serviceId\":25,\"fetcherId\":8,\"createdBy\":\"admin\"}";
		RequestStatus status = serviceController.configureServiceFetcher(serviceFetcherDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testAddUpdateServiceFetcherDataWithServiceFetcherArgAsJsonNull()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":25,\"fetcherId\":8,\"serviceFetcherArgs\":null,\"modifiedBy\":\"admin\"}";
		RequestStatus status = serviceController.configureServiceFetcher(serviceFetcherDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testUpdateServiceFetcherData()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":26,\"fetcherId\":8,\"serviceFetcherArgs\":{\"optional\": {\"updathumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"updatbaseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"modifiedBy\":\"updateuser\"}";
		RequestStatus status = serviceController.configureServiceFetcher(serviceFetcherDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testUpdateServiceFetcherDataWithServiceFetcherArgAsNull()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":26,\"fetcherId\":8,\"modifiedBy\":\"updateuser\"}";
		RequestStatus status = serviceController.configureServiceFetcher(serviceFetcherDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testUpdateServiceFetcherDataWithServiceFetcherArgAsJsonNull()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":26,\"fetcherId\":8,\"serviceFetcherArgs\":null,\"modifiedBy\":\"updateuser\"}";
		RequestStatus status = serviceController.configureServiceFetcher(serviceFetcherDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceFetcherDataWithServiceIdAsNull() throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":0,\"fetcherId\":8,\"serviceFetcherArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		serviceController.configureServiceFetcher(serviceFetcherDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddUpdateServiceFetcherDataWithFetcherIdAsNull()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":25,\"serviceFetcherArgs\":{\"optional\": {\"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		serviceController.configureServiceFetcher(serviceFetcherDataReq);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testAddUpdateServiceFetcherDataWithInvalidJson()throws Exception{
		
		String serviceFetcherDataReq="{\"serviceId\":25,\"fetcherId\":8,\"serviceFetcherArgs\":{\"optional\": \"thumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"baseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}},\"createdBy\":\"admin\"}";
		serviceController.configureServiceFetcher(serviceFetcherDataReq);	
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testInsertupdateServiceFetcherWithDBException() throws DBException{
		ServiceFetcherDetail bean = new ServiceFetcherDetail();
		ServiceMaster srv = new ServiceMaster();
		srv.setServiceId(Long.parseLong("1324687"));
		bean.setServiceMaster(srv);
		dataService.mergeServiceFetcherMapper(bean);
	}
	
	//Service History test Cases
	@Test
	public void testInsertServiceHistoryForFetcher() throws Exception{
		String serviceHistory="{\"serviceId\":25,\"type\":\"fetcher\",\"change\":[{\"optional\": {\"updathumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"updatbaseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}}],\"createdBy\":\"updateuser\"}";
		RequestStatus status = serviceController.logHistory(serviceHistory);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Service History Successfuly Recorded");
	}
	
	@Test
	public void testInsertServiceHistoryForParser() throws Exception{
		String serviceHistory="{\"serviceId\":25,\"type\":\"parser\",\"change\":[{\"optional\": {\"updathumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"updatbaseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}}],\"createdBy\":\"updateuser\"}";
		RequestStatus status = serviceController.logHistory(serviceHistory);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Service History Successfuly Recorded");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testInsertServiceHistoryWithChangeAsNull() throws Exception{
		String serviceHistory="{\"serviceId\":25,\"type\":\"parser\",\"createdBy\":\"updateuser\"}";
		serviceController.logHistory(serviceHistory);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testInsertServiceHistoryWithInvalidChangeJson() throws Exception{
		String serviceHistory="{\"serviceId\":25,\"type\":\"parser\",\"change\":[{\"o\"}],\"createdBy\":\"updateuser\"}";
		serviceController.logHistory(serviceHistory);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testInsertServiceHistoryWithCreatedByAsNull() throws Exception{
		String serviceHistory="{\"serviceId\":25,\"type\":\"parser\",\"change\":[{\"optional\": {\"updathumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"updatbaseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}}]}";
		serviceController.logHistory(serviceHistory);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testInsertServiceHistoryWithServiceIdAsNull() throws Exception{
		String serviceHistory="{\"type\":\"parser\",\"change\":[{\"optional\": {\"updathumbnailImageLocation\": \"1\", \"characterSeperatingTitleAndArtist\": \"-\"}, \"mandatory\": {\"updatbaseUrl\": \"https://www.ndr.de/\", \"sizeOfList\": \"12\", \"titleLocation\": \"3\", \"artistLocation\": \"2\", \"client-unique-id\": \"deu001\", \"playTimeLocation\": \"1\", \"whichListToProcess\": \"1\", \"areListHeadingsPresent\": \"0\", \"areTimesInAscendingOrder\": \"0\", \"areArtistAndTitleInOnePhrase\": \"1\"}}],\"createdBy\":\"updateuser\"}";
		serviceController.logHistory(serviceHistory);
	}
	
	
	//Service Schedule test Cases
	@Test
	public void testaddUpdateServiceSchedule() throws Exception{
		String serviceSchedule = "{\"serviceId\":\"26\", \"frequency\" : \"hourly\",\"startTime\":\"2018-08-08 08:14\", \"customFrequency\":{\"Thursday\":[\"10:00\"]} ,\"isActive\":1, \"timezone\":\"Europe/Copenhagen\", \"createdBy\":\"admin\"}";
		RequestStatus status = serviceController.scheduleService(serviceSchedule);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
		
	}

	@Test(expectedExceptions=ValidationException.class)
	public void testaddUpdateServiceScheduleWithCreatedByAsNull() throws Exception{
		String serviceSchedule = "{\"serviceId\":\"24\", \"frequency\" : \"hourly\",\"startTime\":\"2018-08-08 08:14\", \"customFrequency\":{\"Thursday\":[\"10:00\"]} ,\"isActive\":1, \"timezone\":\"Europe/Copenhagen\"}";
		serviceController.scheduleService(serviceSchedule);
		
	}
	
	@Test
	public void testUpdateServiceSchedule() throws Exception{
		String serviceSchedule = "{\"serviceId\":\"25\", \"frequency\" : \"hourly\",\"startTime\":\"2018-08-08 08:14\", \"customFrequency\":{\"Thursday\":[\"10:00\"]} ,\"isActive\":0, \"timezone\":\"Europe/Copenhagen\", \"modifiedBy\":\"admin\"}";
		RequestStatus status = serviceController.scheduleService(serviceSchedule);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
		
	}
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateServiceScheduleWithModifiedByAsNull() throws Exception{
		String serviceSchedule = "{\"serviceId\":\"25\", \"frequency\" : \"hourly\",\"startTime\":\"2018-08-08 08:14\", \"customFrequency\":{\"Thursday\":[\"10:00\"]} ,\"isActive\":1, \"timezone\":\"Europe/Copenhagen\"}";
		serviceController.scheduleService(serviceSchedule);
		
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testaddUpdateServiceScheduleWithServiceIdAsNull() throws Exception{
		String serviceSchedule = "{\"frequency\" : \"hourly\",\"startTime\":\"2018-08-08 08:14\", \"customFrequency\":{\"Thursday\":[\"10:00\"]} ,\"isActive\":1, \"timezone\":\"Europe/Copenhagen\", \"createdBy\":\"admin\"}";
		serviceController.scheduleService(serviceSchedule);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testaddUpdateServiceScheduleWithInvalidRequest() throws Exception{
		String serviceSchedule = "{\"serviceId\":\"25\",\"frequency\" \"hourly\",\"startTime\":\"2018-08-08 08:14\", \"customFrequency\":{\"Thursday\":[\"10:00\"]} ,\"isActive\":1, \"timezone\":\"Europe/Copenhagen\", \"createdBy\":\"admin\"}";
		serviceController.scheduleService(serviceSchedule);
	}
}
