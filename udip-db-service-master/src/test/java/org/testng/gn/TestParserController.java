package org.testng.gn;

import org.gn.udip.UDIPSQLConnectMain;
import org.gn.udip.controller.ParserController;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.ParserMasterPojo;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

@SpringBootTest(classes = UDIPSQLConnectMain.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@EnableJpaRepositories(basePackages = "org.gn.udip.repository")
public class TestParserController extends AbstractTestNGSpringContextTests{
	
	@Autowired
	ParserController parserController;
	
	@Autowired
	DataService dataService;
	
	@Test
	public void testAddParserData()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		RequestStatus status = parserController.addParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testAddParserDataWithMandatoryArgsOptionalArgsAsNull()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		RequestStatus status = parserController.addParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testAddParserDataWithMandatoryArgsOptionalArgsAsEmpty()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[],\"optionalArgs\":[],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		RequestStatus status = parserController.addParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddParserDataWithParserNameAsNull()throws Exception{
		
		String parserDataReq="{\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddParserDataWithScriptNameAsNull()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddParserDataWithParserCommandAsEmpty()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testAddParserDataWithCreatedByAsNull()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\"}";
		parserController.addParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testAddParserDataWithInvalidJson()throws Exception{
		
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testInsertParserMasterWithDBException() throws DBException{
		
		ParserMasterPojo parserMasterJson=new ParserMasterPojo();
		dataService.insertParserMaster(parserMasterJson);
	}
	
	@Test
	public void testUpdateParserData()throws Exception{
		
		String parserDataReq="{\"parserId\":14,\"parserName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		RequestStatus status = parserController.updateParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateParserDataWithMandatoryArgsOptionalArgsAsNull()throws Exception{
		
		String parserDataReq="{\"parserId\":14,\"parserName\":\"testupdate\",\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		RequestStatus status = parserController.updateParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test
	public void testUpdateParserDataWithMandatoryArgsOptionalArgsAsEmpty()throws Exception{
		
		String parserDataReq="{\"parserId\":14,\"parserName\":\"testupdate\",\"mandatoryArgs\":[],\"optionalArgs\":[],\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		RequestStatus status = parserController.updateParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateParserDataWithParserIdAsNull()throws Exception{
		
		String parserDataReq="{\"parserName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		parserController.updateParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateParserDataWithModifiedByAsEmpty()throws Exception{
		
		String parserDataReq="{\"parserId\":30,\"parserName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"\"}";
		parserController.updateParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testUpdateParserDataWithInvalidJson()throws Exception{
		
		String parserDataReq="{\"parserId\":14,\"parserName\":\"testupdate\",\"mandatoryArgs\":{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		parserController.updateParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testUpdateParserMasterWithDBException() throws Exception{
		
		String parserDataReq="{\"parserId\":7,\"parserName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"parserCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		parserController.updateParserData(parserDataReq);
	}
	
	@Test
	public void testDeleteParserData()throws Exception{
		
		ParserMasterPojo parserDataReq=new ParserMasterPojo();
		parserDataReq.setParserId(Long.valueOf(14));
		parserDataReq.setModifiedBy("testuser");
		RequestStatus status = parserController.deleteParserData(parserDataReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteParserDataWithParserIdAsNull()throws Exception{
		
		ParserMasterPojo parserDataReq=new ParserMasterPojo();
		parserDataReq.setModifiedBy("testuser");
		parserController.deleteParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteParserDataWithModifiedByAsNull()throws Exception{
		
		ParserMasterPojo parserDataReq=new ParserMasterPojo();
		parserDataReq.setParserId(Long.valueOf(14));
		parserController.deleteParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteParserDataWithModifiedByAsEmpty()throws Exception{
		
		ParserMasterPojo parserDataReq=new ParserMasterPojo();
		parserDataReq.setParserId(Long.valueOf(14));
		parserDataReq.setModifiedBy("");
		parserController.deleteParserData(parserDataReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testDeleteParserDataWithDBException()throws Exception{
		
		ParserMasterPojo parserDataReq=new ParserMasterPojo();
		parserDataReq.setParserId(Long.valueOf(7));
		parserDataReq.setModifiedBy("testuser");
		parserController.deleteParserData(parserDataReq);
	}

}
