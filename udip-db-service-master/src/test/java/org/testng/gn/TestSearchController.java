package org.testng.gn;

import javax.transaction.Transactional;

import org.gn.udip.UDIPSQLConnectMain;
import org.gn.udip.config.AppProperties;
import org.gn.udip.controller.FetcherController;
import org.gn.udip.controller.ParserController;
import org.gn.udip.controller.SearchController;
import org.gn.udip.controller.ServiceController;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.model.ServiceCreationResponse;
import org.gn.udip.model.ServiceMasterPojo;
import org.gn.udip.service.search.vo.FetcherSearchResult;
import org.gn.udip.service.search.vo.ParserSearchResult;
import org.gn.udip.service.search.vo.SearchResult;
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
public class TestSearchController extends AbstractTestNGSpringContextTests {
	
	
	@Autowired
	SearchController searchController;
	
	
	@BeforeClass
	public void init() {
	}

	@AfterClass
	public void destroy() {
	}
	
	@Autowired
	ServiceController serviceController;
	
	@Autowired
	FetcherController fetcherController;
	
	@Autowired
	ParserController parserController;
	
	@Test
	private void testGlobalSearch() throws Exception {
		
		searchController.createIndex();
		
		//Add Dummy Service for Search
		ServiceMasterPojo serviceDataReq=new ServiceMasterPojo();
		serviceDataReq.setServiceName("test http");
		serviceDataReq.setUniqueId("testid");
		serviceDataReq.setCreatedBy("admin");
		serviceController.addServiceData(serviceDataReq);

		//Add Dummy Fetcher for Search
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq);
		
		
		//Add Dummy Parser for Search
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
				
		
		SearchResult result = searchController.search("test");
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getServiceSearchResult().getCount()>0);
		Assert.assertTrue(result.getServiceSearchResult().getServices().size()>0);
		Assert.assertTrue(result.getFetcherSearchResult().getCount()>0);
		Assert.assertTrue(result.getFetcherSearchResult().getFetchers().size()>0);
		Assert.assertTrue(result.getParserSearchResult().getCount()>0);
		Assert.assertTrue(result.getParserSearchResult().getParsers().size()>0);
		
		/*System.out.println("1 :"+(result.getServiceSearchResult().getCount()));
		System.out.println("2 :"+ (result.getServiceSearchResult().getServices().size()));
		System.out.println("3 :"+(result.getFetcherSearchResult().getCount()));
		System.out.println("4 :"+ (result.getFetcherSearchResult().getFetchers().size()));
		System.out.println("5 :"+ (result.getParserSearchResult().getCount()));
		System.out.println("6 :"+ (result.getParserSearchResult().getParsers().size()));*/
		
		
	}
	
	@Test
	private void testGlobalSearchWithSpaces() throws Exception {
		SearchResult result = searchController.search("test admin");
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getServiceSearchResult().getCount()>0);
		Assert.assertTrue(result.getServiceSearchResult().getServices().size()>0);
		Assert.assertTrue(result.getFetcherSearchResult().getCount()>0);
		Assert.assertTrue(result.getFetcherSearchResult().getFetchers().size()>0);
		Assert.assertTrue(result.getParserSearchResult().getCount()>0);
		Assert.assertTrue(result.getParserSearchResult().getParsers().size()>0);
	}
	
	@Test
	private void testGlobalSearchForJunkString() throws Exception {
		
		SearchResult result = searchController.search("asjdbakljsd");
		Assert.assertNotNull(result);
		
		Assert.assertTrue(result.getServiceSearchResult().getCount()==0);
		Assert.assertTrue(result.getServiceSearchResult().getServices().size()==0);
		Assert.assertTrue(result.getFetcherSearchResult().getCount()==0);
		Assert.assertTrue(result.getFetcherSearchResult().getFetchers().size()==0);
		Assert.assertTrue(result.getParserSearchResult().getCount()==0);
		Assert.assertTrue(result.getParserSearchResult().getParsers().size()==0);
		
		/*System.out.println("2.1 :"+(result.getServiceSearchResult().getCount()));
		System.out.println("2.2 :"+ (result.getServiceSearchResult().getServices().size()));
		System.out.println("2.3 :"+(result.getFetcherSearchResult().getCount()));
		System.out.println("2.4 :"+ (result.getFetcherSearchResult().getFetchers().size()));
		System.out.println("2.5 :"+ (result.getParserSearchResult().getCount()));
		System.out.println("2.6 :"+ (result.getParserSearchResult().getParsers().size()));*/
		
	}
	
	@Test
	private void testFetcherSearch() throws Exception {
		
		//Add Dummy Fetcher for Search
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq); 
				
		FetcherSearchResult result = searchController.searchFetcherMaster("test", 1, 10);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getCount()>0);
		Assert.assertTrue(result.getFetchers().size()>0);
	}
	
	@Test
	private void testFetcherSearchWithSpaces() throws Exception {
		
		//Add Dummy Fetcher for Search
		String fetcherAddReq="{\"fetcherName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"fetcherCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		fetcherController.createFetcher(fetcherAddReq);
		
		FetcherSearchResult result = searchController.searchFetcherMaster("test admin", 1, 10);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getCount()>0);
		Assert.assertTrue(result.getFetchers().size()>0);
	}
	
	@Test
	private void testFetcherSearchForJunkString() throws Exception {
		FetcherSearchResult result = searchController.searchFetcherMaster("asdgaishgd", 1, 10);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getCount()==0);
		Assert.assertTrue(result.getFetchers().size()==0);
	}
	
	@Test
	private void testParserSearch() throws Exception {
		
		//Add Dummy Parser for Search
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
		ParserSearchResult result = searchController.searchParserMaster("test", 1, 10);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getCount()>0);
		Assert.assertTrue(result.getParsers().size()>0);
	}
	
	@Test
	private void testParserSearchWithSpaces() throws Exception {
		
		//Add Dummy Parser for Search
		String parserDataReq="{\"parserName\":\"test\",\"mandatoryArgs\":[{\"client-unique-id\":\"\"},{\"baseUrl\":\"\"},{\"playTimeLocation\":\"\"},{\"titleLocation\":\"\"},{\"artistLocation\":\"\"},{\"areArtistAndTitleInOnePhrase\":\"\"},{\"areListHeadingsPresent\":\"\"},{\"whichListToProcess\":\"\"},{\"sizeOfList\":\"\"},{\"areTimesInAscendingOrder\":\"\"}],\"optionalArgs\":[{\"thumbnailImageLocation\":\"\"},{\"characterSeperatingTitleAndArtist\":\"\"},{\"extraWordsInArtist\":\"\"},{\"extraWordsInTitle\":\"\"},{\"placeholderText\":\"\"}],\"scriptName\":\"testscript\",\"parserCommand\":\"php cli_init.php --args='{}'\",\"createdBy\":\"admin\"}";
		parserController.addParserData(parserDataReq);
		
		ParserSearchResult result = searchController.searchParserMaster("test admin", 1, 10);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getCount()>0);
		Assert.assertTrue(result.getParsers().size()>0);
	}
	
	@Test
	private void testParserSearchForJunkString() throws Exception {
		ParserSearchResult result = searchController.searchParserMaster("asdhasid", 1, 10);
		Assert.assertNotNull(result);
		Assert.assertTrue(result.getCount()==0);
		Assert.assertTrue(result.getParsers().size()==0);
	}
	

}
