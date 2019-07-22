package org.gn.udip.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppProperties {
	
	AppProperties(@Value("${udis.mailer.id}") String mailId,
			@Value("${udis.mailer.template}") String template,
			@Value("${udis.mailer.subscriber.name}") String subsName,
			@Value("${udis.mailer.subject}") String subject,
			@Value("${truncate.testdata.expiry.limit}") Integer expLimit,
			@Value("${hb.search.offset}") Integer searchOffset,
			@Value("${hb.search.range}") Integer searchRange)
	{
		AppProperties.MAILER_ID=mailId;
		AppProperties.MAILER_TEMPLATE=template;
		AppProperties.MAILER_SUBSCRIBER_NAME= subsName;
		AppProperties.MAILER_SUBJECT =subject;
		AppProperties.EXPLIMIT=expLimit;
		AppProperties.HB_SEARCH_OFFSET=searchOffset;
		AppProperties.HB_SEARCH_RANGE=searchRange;
		
	}
	
	public static String MAILER_ID;
	
	public static String MAILER_TEMPLATE;
	
	public static String MAILER_SUBSCRIBER_NAME;
	
	public static String MAILER_SUBJECT;
	
	public static Integer EXPLIMIT;
	
	public static Integer HB_SEARCH_OFFSET;
	
	public static Integer HB_SEARCH_RANGE;

}
