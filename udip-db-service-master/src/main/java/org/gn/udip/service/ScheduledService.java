package org.gn.udip.service;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.gn.udip.config.AppProperties;
import org.gn.udip.config.BuildSearchIndex;
import org.gn.udip.exception.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
	
	@Autowired
	DataService dataService;
	
	@Autowired
	BuildSearchIndex indexBuilder;

	private static Logger logger = Logger.getLogger(ScheduledService.class);
	
	@Scheduled(cron = "${truncate.testdata.cron.exp}")
    public void scheduleTask() {        
        logger.info("Initiating service for truncating test data.");
        
        Calendar cal = Calendar.getInstance();
		Date today = cal.getTime();
		logger.info("Current date is : "+ today);
		logger.info("Calculating expiry date.");
		cal.add(Calendar.DATE, AppProperties.EXPLIMIT);
		Date expiryDate = cal.getTime();
		logger.info("Expiry Date calculated is: " +expiryDate);
		
		try {
			dataService.truncateTestData(expiryDate);
		} catch (DBException e) {
			logger.error("Service for truncating test data failed. "+e.getErrDesc());
		}
	}
	
	
	@Scheduled(cron = "${hb.search.cron.exp}")
    public void scheduleIndex() {        
        logger.info("Initiating service for Indexing");
		try {
			indexBuilder.startIndexing();
		} catch (Exception e) {
			logger.error("Service for Indexing Failed");
			logger.error(e);
		}
	}
}
