package org.gn.udip.config;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Component;

@Component
@Transactional
public class BuildSearchIndex {

	@PersistenceContext
	private EntityManager entityManager;
	
	private static Logger logger = Logger.getLogger(BuildSearchIndex.class);

	/**
	 * Create an initial Lucene index for the data already present in the
	 * database. This method is called when Spring's startup.
	 */

	public void startIndexing() {
		try {
			FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
			fullTextEntityManager.createIndexer().startAndWait();
		} catch (Exception e) {
			logger.error("An error occurred trying to build the serach index: " + e.getMessage());
			logger.error(e);
		}
		return;
	}

}
