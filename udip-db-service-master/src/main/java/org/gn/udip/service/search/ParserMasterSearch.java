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
import org.gn.udip.model.ParserMaster;
import org.gn.udip.service.search.vo.ParserSearchResult;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParserMasterSearch {

	@Autowired
	private GlobalSearchService globalSearchService;

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = Logger.getLogger(ParserMasterSearch.class);

	@Transactional
	public ParserSearchResult search(String text) {
		// first check if it is a phrase
		// if yes search for phrase with word skips allowed
		// then split based on space
		// call search method for each in MUST bool query

		// else if only one word
		// call search method

		List<ParserMaster> parserMasters = null;
		int parserMasterCount = 0;
		text = text.trim();
		if (text.contains(" ")) {
			parserMasters = new ArrayList<ParserMaster>();

			// phrase search and count
			parserMasters = searchParserMasterList(text, QueryTypeEnum.PHRASE);
			parserMasterCount = searchCount(text, QueryTypeEnum.PHRASE);
		}

		else {
			// String search and count
			parserMasters = searchParserMasterList(text, QueryTypeEnum.STRING);
			parserMasterCount = searchCount(text, QueryTypeEnum.STRING);
		}
		
		return new ParserSearchResult(parserMasterCount, parserMasters);
		
	}
	
	@Transactional
	public ParserSearchResult search(String text,int start,int end) {
		// first check if it is a phrase
		// if yes search for phrase with word skips allowed
		// then split based on space
		// call search method for each in MUST bool query

		// else if only one word
		// call search method

		List<ParserMaster> parserMasters = null;
		int parserMasterCount = 0;
		text = text.trim();
		if (text.contains(" ")) {
			parserMasters = new ArrayList<ParserMaster>();

			// phrase search and count
			parserMasters = searchList(text, QueryTypeEnum.PHRASE,start,end);
			parserMasterCount = searchCount(text, QueryTypeEnum.PHRASE);
		}

		else {
			// String search and count
			parserMasters = searchList(text, QueryTypeEnum.STRING,start,end);
			parserMasterCount = searchCount(text, QueryTypeEnum.STRING);
		}
		
		return new ParserSearchResult(parserMasterCount, parserMasters);
		
	}

	public List<ParserMaster> searchParserMasterList(String text, QueryTypeEnum queryType) {
		return searchList(text, queryType, AppProperties.HB_SEARCH_OFFSET,AppProperties.HB_SEARCH_RANGE);
	}

	@SuppressWarnings("unchecked")
	private List<ParserMaster> searchList(String text, QueryTypeEnum queryType, int start, int end) {

		logger.info("Inside searchList. Searching for term " + text);
		List<ParserMaster> list = null;
		
		try {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			list = fullTextEntityManager.createFullTextQuery(generateQuery(fullTextEntityManager, text, queryType),ParserMaster.class)
					.setFirstResult(start).setMaxResults(end).getResultList();
		} catch (Exception e) {
			logger.error(e);
		}
		return list;
	}

	private int searchCount(String text, QueryTypeEnum queryType) {
		logger.info("Inside searchCount. Searching for term " + text);
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
		return fullTextEntityManager.createFullTextQuery(generateQuery(fullTextEntityManager, text, queryType),ParserMaster.class)
				.getResultSize();
	}

	private Query generateQuery(FullTextEntityManager ftem, String text, QueryTypeEnum queryType) {
		
		QueryBuilder qb = ftem.getSearchFactory().buildQueryBuilder().forEntity(ParserMaster.class).get();

		BooleanQuery.Builder finalBoolQueryBuilder = new BooleanQuery.Builder();
		
		// IsActive MUST be 1
		finalBoolQueryBuilder.add(qb.keyword().onField("isActive").matching("1").createQuery(), Occur.MUST);

		switch (queryType) {
		case PHRASE:
			// this is the tricky part.
			
			
			
			// 1. apply phrase query and add big boost and add to textBoolQuery
			// with SHOULD clause
			BooleanQuery.Builder textBoolQueryBuilder = new BooleanQuery.Builder();

			textBoolQueryBuilder.add(
					qb.phrase()
					.withSlop(2)
					.onField("parserName")
					.andField("createdBy")
					.sentence(text)
					.createQuery(), Occur.SHOULD);

			// 2. Add each word to wildcard query and add it to tempBoolQuery
			// with SHOULD clause
			// Then add the whole thing to textBoolQuery with a SHOULD clause
			String textKeys[] = text.split(" ");

			for (String textKey : textKeys) {
				textBoolQueryBuilder.add(
						qb.keyword()
						.wildcard()
						.onField("parserName")
						.andField("createdBy")
						.matching(globalSearchService.generateWildcardKeyword(textKey.trim()))
						.createQuery(),	Occur.SHOULD);
			}

			// Final query contains 2 conditions
			// 1 - isActive MUST be 0
			// 2 - some part of the text MUST be found
			finalBoolQueryBuilder.add(textBoolQueryBuilder.build(), Occur.MUST);

			break;

		case STRING:
			finalBoolQueryBuilder.add(
					qb.keyword()
					.wildcard()
					.onField("parserName").boostedTo(5.5f)
					.andField("createdBy")
					.matching(globalSearchService.generateWildcardKeyword(text.trim())).createQuery(), Occur.MUST);
			break;

		default:
			break;
		}

		Query finalQuery = finalBoolQueryBuilder.build();
		logger.info("QUERY created by method generateQueryForParserMaster - " + finalQuery);
		return finalQuery;
	}

}
