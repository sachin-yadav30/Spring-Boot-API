package org.gn.udip.service.search;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.BooleanClause.Occur;
import org.gn.udip.config.AppProperties;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.service.search.vo.ServiceSearchResult;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/** This class includes functions to search in ServiceMaster class.
 * @author Ankita
 *
 */
@Service
public class ServiceMasterSearch {
	
	@Autowired
	private GlobalSearchService globalSearchService;

	// Spring will inject here the entity manager object
	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(ServiceMasterSearch.class);



	/**Global Search service.
	 * Based on type of search string, it applies phrase and/or keyword search
	 * If phrase present, it will still apply keyword search on each word present in the phrase
	 * NOTE - All keyword searches are WILDCARD
	 * @param text
	 * @return
	 */
	@Transactional
	public ServiceSearchResult search(String text) {
		//first check if it is a phrase
			//if yes search for phrase with word skips allowed
			//then split based on space
			//call search method for each in MUST bool query
		
		//else if only one word
			//call search method

		List<ServiceMaster> serviceMasters = null;
		int serviceMasterCount =0;
		text = text.trim();
		if(text.contains(" ")){
			serviceMasters = new ArrayList<ServiceMaster>();
			
			//phrase search and count
			serviceMasters = searchServiceMasterList(text, QueryTypeEnum.PHRASE);
			serviceMasterCount = searchCount(text, QueryTypeEnum.PHRASE);
		}

		else {
			//String search and count
			serviceMasters = searchServiceMasterList(text, QueryTypeEnum.STRING);
			serviceMasterCount = searchCount(text, QueryTypeEnum.STRING);
		}
		return new ServiceSearchResult(serviceMasterCount, serviceMasters);
	}
	
	@Transactional
	public ServiceSearchResult search(String text,int start,int end) {
		//first check if it is a phrase
			//if yes search for phrase with word skips allowed
			//then split based on space
			//call search method for each in MUST bool query
		
		//else if only one word
			//call search method

		List<ServiceMaster> serviceMasters = null;
		int serviceMasterCount =0;
		text = text.trim();
		if(text.contains(" ")){
			serviceMasters = new ArrayList<ServiceMaster>();
			
			//phrase search and count
			serviceMasters = searchList(text, QueryTypeEnum.PHRASE,start,end);
			serviceMasterCount = searchCount(text, QueryTypeEnum.PHRASE);
		}

		else {
			//String search and count
			serviceMasters = searchList(text, QueryTypeEnum.PHRASE,start,end);
			serviceMasterCount = searchCount(text, QueryTypeEnum.STRING);
		}
		return new ServiceSearchResult(serviceMasterCount, serviceMasters);
	}


	/** Default value of start and end are 0 and 10 respectively
	 * @param text
	 * @return
	 */
	public List<ServiceMaster> searchServiceMasterList(String text, QueryTypeEnum queryType) {
		return searchList(text, queryType, AppProperties.HB_SEARCH_OFFSET,AppProperties.HB_SEARCH_RANGE);
	}


	/** Search ServiceMaster entity for a string 'text'
	 * @param text
	 * @param queryType Enum
	 * @param start
	 * @param end
	 * @return list of ServiceMaster entities
	 */
	@SuppressWarnings("unchecked")
	private List<ServiceMaster> searchList(String text, QueryTypeEnum queryType, int start, int end) {

		logger.info("Inside searchList. Searching for term "+text);
		List<ServiceMaster> list = null;
		
		try{
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		list = fullTextEntityManager
				.createFullTextQuery(generateQuery(fullTextEntityManager, text, queryType),ServiceMaster.class)
				.setFirstResult(start)
				.setMaxResults(end)
				.getResultList();
		}
		catch (Exception e) {
			logger.error(e);
		}
		return list;
	}


	/** Get count of all search results
	 * @param text
	 * @return integer
	 * @param queryType Enum
	 */
	private int searchCount(String text, QueryTypeEnum queryType) {
		logger.info("Inside searchCount. Searching for term "+text);

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		return fullTextEntityManager
				.createFullTextQuery(generateQuery(fullTextEntityManager, text, queryType),ServiceMaster.class)
				.getResultSize();
	}

	/**Generate search query for ServiceMaster entity
	 * @param ftem FullTextEntityManager
	 * @param text String search term
	 * @param queryType Enum
	 * @return Query
	 */
	private Query generateQuery(FullTextEntityManager ftem, String text, QueryTypeEnum queryType) {
		
		QueryBuilder qb = ftem
				.getSearchFactory()
				.buildQueryBuilder()
				.forEntity(ServiceMaster.class)
				.get();

		BooleanQuery.Builder finalBoolQueryBuilder = new BooleanQuery.Builder();
		
		//IsActive MUST be 1
		finalBoolQueryBuilder.add(qb.keyword().onField("isActive").matching("1").createQuery(), Occur.MUST);
		
		switch (queryType) {
		case PHRASE:
			//this is the tricky part.
			//1. apply phrase query and add big boost and add to textBoolQuery with SHOULD clause
			BooleanQuery.Builder textBoolQueryBuilder = new BooleanQuery.Builder();
			
			
			
			textBoolQueryBuilder.add(
			qb
			.phrase()
			.withSlop(2)
			.onField("serviceName")//.boostedTo(100f)
			.andField("uniqueId")//.boostedTo(50f)
			.andField("modifiedBy")
			.andField("createdBy")
			.andField("owner")
			.andField("serviceFetcherDetail.serviceFetcherArgs")
			.andField("serviceParserDetail.serviceParserArgs")
			.sentence(text)
			.createQuery(), Occur.SHOULD);
			
			//2. Add each word to wildcard query and add it to tempBoolQuery with SHOULD clause
			//Then add the whole thing to textBoolQuery with a SHOULD clause
			String textKeys[] = text.split(" ");
			
			for (String textKey : textKeys) {
				textBoolQueryBuilder.add(qb
				.keyword()
				.wildcard()
				.onField("serviceName")//.boostedTo(5.5f)
				.andField("uniqueId")//.boostedTo(2.5f)
				.andField("createdBy")
				.andField("owner")
				.andField("serviceFetcherDetail.serviceFetcherArgs")
				.andField("serviceParserDetail.serviceParserArgs")
				.matching(globalSearchService.generateWildcardKeyword(textKey.trim()))
				.createQuery(), Occur.SHOULD);
			}
			
			//Final query contains 2 conditions
			//1 - isActive MUST be 0
			//2 - some part of the text MUST be found
			finalBoolQueryBuilder.add(textBoolQueryBuilder.build(), Occur.MUST);
			
			break;

		case STRING:
			finalBoolQueryBuilder.add(qb
			.keyword()
			.wildcard()
			.onField("serviceName").boostedTo(5.5f)
			.andField("uniqueId").boostedTo(2.5f)
			.andField("createdBy")
			.andField("owner")
			.andField("serviceFetcherDetail.serviceFetcherArgs")
			.andField("serviceParserDetail.serviceParserArgs")
			.matching(globalSearchService.generateWildcardKeyword(text.trim()))
			.createQuery(), Occur.MUST);
			break;

		default:
			break;
		}
		
		Query finalQuery =finalBoolQueryBuilder.build(); 
		logger.info("QUERY created by method generateQueryForServiceMaster - "+ finalQuery);
		return finalQuery;
	}
}
