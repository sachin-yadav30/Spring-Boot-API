package org.testng.gn;

import org.gn.udip.UDIPSQLConnectMain;
import org.gn.udip.controller.FetcherController;
import org.gn.udip.exception.DBException;
import org.gn.udip.exception.ValidationException;
import org.gn.udip.model.FetcherMaster;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

@SpringBootTest(classes = UDIPSQLConnectMain.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@EnableJpaRepositories(basePackages = "org.gn.udip.repository")
public class TestFetcherController extends AbstractTestNGSpringContextTests {
	
	@BeforeClass
	public void init() {
	}

	@AfterClass
	public void destroy() {
	}
	
	@Autowired
	FetcherController fetcherController;
	
	@Autowired
	DataService dataService;
	
		
	@Test
	public void createFetcher() throws Exception{
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		RequestStatus status = fetcherController.createFetcher(fetcherAddReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Added");
		
	}
	
	@Test
	public void testCreateFetcherWithMandatoryArgsOptionalArgsAsNull() throws Exception {
		
		String fetcherAddReq="{\"fetcherName\":\"test1\",\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		RequestStatus status = fetcherController.createFetcher(fetcherAddReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Added");
	}
	
	@Test
	public void testCreateFetcherWithMandatoryArgsOptionalArgsAsEmpty() throws Exception {
		
		String fetcherAddReq="{\"fetcherName\":\"test2\",\"mandatoryArgs\":[],\"optionalArgs\":[],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		RequestStatus status = fetcherController.createFetcher(fetcherAddReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Added");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testCreateFetcherWithNameAsNull()throws Exception{
		
		String fetcherAddReq="{\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testCreateFetcherWithScriptNameAsNull()throws Exception{
		
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testCreateFetcherWithCommandAsEmpty()throws Exception{
		
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testCreateFetcherWithCreatedByAsNull()throws Exception{
		
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\"}";
		fetcherController.createFetcher(fetcherAddReq);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testAddParserDataWithInvalidJson()throws Exception{
		
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testCreateFetcherWithDBException() throws Exception{
		dataService.createFetcherMaster(new FetcherMaster());
	}
	
	
	@Test
	public void testUpdateFetcher()throws Exception{
		
		String fetcherUpdReq="{\"fetcherId\":8,\"fetcherName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		RequestStatus status = fetcherController.updateFetcher(fetcherUpdReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testUpdateFetcherWithMandatoryArgsOptionalArgsAsNull()throws Exception{
		
		String fetcherUpdReq="{\"fetcherId\":8,\"fetcherName\":\"testupdate\",\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		RequestStatus status = fetcherController.updateFetcher(fetcherUpdReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test
	public void testUpdateFetcherWithMandatoryArgsOptionalArgsAsEmpty()throws Exception{
		
		String fetcherUpdReq="{\"fetcherId\":8,\"fetcherName\":\"testupdate\",\"mandatoryArgs\":[],\"optionalArgs\":[],\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		RequestStatus status = fetcherController.updateFetcher(fetcherUpdReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Updated");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateFetcherWithFetcherIdAsNull()throws Exception{
		
		String fetcherUpdReq="{\"fetcherName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		fetcherController.updateFetcher(fetcherUpdReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testUpdateFetcherWithModifiedByAsEmpty()throws Exception{
		
		String fetcherUpdReq="{\"fetcherId\":8,\"fetcherName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"\"}";
		fetcherController.updateFetcher(fetcherUpdReq);
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testUpdateFetcherWithInvalidJson()throws Exception{
		
		String fetcherUpdReq="{\"fetcherId\":8,\"fetcherName\":\"testupdate\",\"mandatoryArgs\":{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		fetcherController.updateFetcher(fetcherUpdReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testUpdateFetcherWithDBException() throws Exception{
		
		String fetcherUpdReq="{\"fetcherId\":99999,\"fetcherName\":\"testupdate\",\"mandatoryArgs\":[{\"client-unique-id\":\"update\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"update\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscriptupdate\",\"fetcherCommand\":\"Update php cli_init.php --args='{}'\",\"modifiedBy\":\"testuser\"}";
		fetcherController.updateFetcher(fetcherUpdReq);
	}
	
	@Test
	public void testDeleteFetcher()throws Exception{
		
		String fetcherDelReq="{\"fetcherId\":8,\"modifiedBy\":\"testuser\"}";
		RequestStatus status = fetcherController.deleteFetcher(fetcherDelReq);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(), "Fetcher Successfuly Deleted");
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteFetcherWithFetcherIdAsNull()throws Exception{
		
		String fetcherDelReq="{\"modifiedBy\":\"testuser\"}";
		fetcherController.deleteFetcher(fetcherDelReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteFetcherWithModifiedByAsNull()throws Exception{
		String fetcherDelReq="{\"modifiedBy\":\"testuser\"}";
		fetcherController.deleteFetcher(fetcherDelReq);
	}
	
	@Test(expectedExceptions=ValidationException.class)
	public void testDeleteFetcherWithModifiedByAsEmpty()throws Exception{
		
		String fetcherDelReq="{\"fetcherId\":8,\"modifiedBy\":\"\"}";
		fetcherController.deleteFetcher(fetcherDelReq);
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testDeleteFetcherWithDBException()throws Exception{
		
		String fetcherDelReq="{\"fetcherId\":999,\"modifiedBy\":\"testUser\"}";
		fetcherController.deleteFetcher(fetcherDelReq);
	}
	
}
