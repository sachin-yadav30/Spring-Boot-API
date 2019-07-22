package org.gn.udip.controller;

import org.apache.log4j.Logger;
import org.gn.udip.config.BuildSearchIndex;
import org.gn.udip.service.search.FetcherMasterSearch;
import org.gn.udip.service.search.GlobalSearchService;
import org.gn.udip.service.search.ParserMasterSearch;
import org.gn.udip.service.search.ServiceMasterSearch;
import org.gn.udip.service.search.vo.FetcherSearchResult;
import org.gn.udip.service.search.vo.ParserSearchResult;
import org.gn.udip.service.search.vo.SearchResult;
import org.gn.udip.service.search.vo.ServiceSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class SearchController {
	
	@Autowired
	GlobalSearchService globalSearchService;
	
	@Autowired
	private ServiceMasterSearch serviceMasterSearch;
	
	@Autowired
	private FetcherMasterSearch fetcherMasterSearch;
	
	@Autowired
	private ParserMasterSearch parserMasterSearch;
	
	@Autowired
	private BuildSearchIndex buildSearchIndex;
	
	private static Logger logger = Logger.getLogger(SearchController.class);
	
	@GetMapping("/search")
	public SearchResult search(@RequestParam("text") String text) throws Exception {
	
		logger.info("Search request recieved for term "+ text);
		//return globalSearchService.search("*"+text+"*");
		return globalSearchService.search(text.toLowerCase());
	}
	
	@GetMapping("/search/service")
	public ServiceSearchResult searchServiceMaster(@RequestParam("text") String text,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize) throws Exception {
	
		logger.info("Search Service request recieved for term "+ text);
		int offset = (pageNumber-1) * pageSize;
		//search serviceMasterSearch for paginated resultset
		return serviceMasterSearch.search(text.toLowerCase(),offset,pageSize);
	}
	
	
	@GetMapping("/search/fetcher")
	public FetcherSearchResult searchFetcherMaster(@RequestParam("text") String text,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize) throws Exception {
	
		logger.info("Search Fetcher request recieved for term "+ text);
		int offset = (pageNumber-1) * pageSize;
		//search serviceMasterSearch for paginated resultset
		return fetcherMasterSearch.search(text.toLowerCase(),offset,pageSize);
	}
	
	@GetMapping("/search/parser")
	public ParserSearchResult searchParserMaster(@RequestParam("text") String text,
			@RequestParam("pageNumber") int pageNumber,
			@RequestParam("pageSize") int pageSize) throws Exception {
	
		logger.info("Search Parser request recieved for term "+ text);
		int offset = (pageNumber-1) * pageSize;
		//search serviceMasterSearch for paginated resultset
		return parserMasterSearch.search(text.toLowerCase(),offset,pageSize);
	}
	
	@GetMapping("/search/sync")
	public boolean createIndex() throws Exception {
		logger.info("Creating indexes");
		buildSearchIndex.startIndexing();
		return true;
	}
}
