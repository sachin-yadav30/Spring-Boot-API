package org.gn.udip.service.search;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.gn.udip.service.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class contains all search related queries.
 * Two types of results are returned - 
 * 1 - count of items found for search term
 * 2 - List of items found for search
 * Search results are paginated, based on parameters start and end. If not provided,
 * by default first 5 results will be returned.
 * 
 * @author Ankita
 *
 */
@Service
@Transactional
public class GlobalSearchService {

	// Spring will inject here the entity manager object
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ServiceMasterSearch serviceMasterSearch;
	
	@Autowired
	private FetcherMasterSearch fetcherMasterSearch;
	
	@Autowired
	private ParserMasterSearch parserMasterSearch;
	
	private static Logger logger = Logger.getLogger(GlobalSearchService.class);
	
	
	/**Global search method
	 *
	 * @param text
	 * @return SearchResultVO object 
	 * containing maximum 5 object of each category
	 *  and count of search results per category 
	 */
	public SearchResult search(String text) {
		
		logger.info("Inside Search. Searching for text "+text);
		SearchResult searchResultVO = new SearchResult();
		searchResultVO.setText(text);
		
		//Searching serviceMaster
		searchResultVO.setServiceSearchResult(serviceMasterSearch.search(text));
		//Searching fetcher master
		searchResultVO.setFetcherSearchResult(fetcherMasterSearch.search(text));
		
		searchResultVO.setParserSearchResult(parserMasterSearch.search(text));
		//Searching parser master
		
		return searchResultVO;
	}
	
	
	/** Common method to generate wildcard text
	 * @param text
	 * @return
	 */
	public String generateWildcardKeyword(String text){
		return "*"+text+"*";
	}
}
