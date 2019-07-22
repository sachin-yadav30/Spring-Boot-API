DROP TABLE IF EXISTS parser_master;
CREATE TABLE parser_master (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45)  NOT NULL,
  mandatory_args varchar(500) ,
  optional_args varchar(500) ,
  scriptname varchar(500) NOT NULL,
  parser_command varchar(500) NOT NULL,
  isActive BOOLEAN NOT NULL DEFAULT '1',
  createdBy varchar(50) DEFAULT NULL,
  modifiedBy varchar(50) DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS service_parser_detail;
CREATE TABLE service_parser_detail (
  id int(11) NOT NULL AUTO_INCREMENT,
  service_master_id int(11) NOT NULL,
  parser_master_id int(11) NOT NULL,
  service_parser_args varchar(500) DEFAULT NULL,
  createdBy varchar(45) DEFAULT NULL,
  modifiedBy varchar(45) DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS category;
CREATE TABLE category (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  isActive BOOLEAN NOT NULL DEFAULT '1',
  mapping_json varchar(500) DEFAULT NULL,
  input_blobtype varchar(45) DEFAULT NULL,
  output_blobtype varchar(45) DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS fetcher_master;
CREATE TABLE fetcher_master (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  mandatory_args varchar(500),
  optional_args varchar(500) ,
  scriptname varchar(500)  NOT NULL,
  fetcher_command varchar(500) NOT NULL,
  fetcher_type int(11) DEFAULT NULL,
  isActive BOOLEAN NOT NULL DEFAULT '1',
  createdBy varchar(50) DEFAULT NULL,
  modifiedBy varchar(50) DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS service_master;
CREATE TABLE service_master (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100)  NOT NULL,
  unique_id varchar(100) NOT NULL,
  vertical varchar(45)  DEFAULT NULL,
  category varchar(45)  DEFAULT NULL,
  owner varchar(45)  DEFAULT NULL,
  status BOOLEAN NOT NULL DEFAULT '1',
  charset varchar(100) DEFAULT NULL,
  fetcher_run_status varchar(45)  DEFAULT NULL,
  parser_run_status varchar(45)  DEFAULT NULL,
  priority varchar(45)  DEFAULT NULL,
  isActive BOOLEAN NOT NULL DEFAULT '1',
  isSchedulable BOOLEAN NOT NULL DEFAULT '0',
  isConcatenatedData tinyint(1) NOT NULL DEFAULT '0',
  createdBy varchar(45)  NOT NULL DEFAULT '0',
  createdOn datetime NOT NULL,
  modifiedBy varchar(45)  DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id)
);



DROP TABLE IF EXISTS notification_master;

CREATE TABLE notification_master (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45)  NOT NULL,
  category varchar(45)  DEFAULT NULL,
  content_medium varchar(500) ,
  createdBy int(11) DEFAULT NULL,
  modifiedBy int(11) DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id),
);

DROP TABLE IF EXISTS service_notification_detail;
CREATE TABLE service_notification_detail (
  id int(11) NOT NULL AUTO_INCREMENT,
  service_id int(11) NOT NULL,
  notification_id int(11) NOT NULL,
  notification_level varchar(45)  NOT NULL,
  PRIMARY KEY (id)
  
);

DROP TABLE IF EXISTS user_notification_detail;

CREATE TABLE user_notification_detail (
  notification_id int(11) NOT NULL AUTO_INCREMENT,
  reception_list text NOT NULL,
  PRIMARY KEY (notification_id)
);


DROP TABLE IF EXISTS service_fetcher_detail;

CREATE TABLE service_fetcher_detail (
  id int(11) NOT NULL AUTO_INCREMENT,
  service_master_id int(11) NOT NULL,
  fetcher_master_id int(11) NOT NULL,
  service_fetcher_args varchar(500) DEFAULT NULL,
  createdBy varchar(45) DEFAULT NULL,
  modifiedBy varchar(45) DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS service_job_status;
CREATE TABLE service_job_status (
  id int(11) NOT NULL AUTO_INCREMENT,
  job_id varchar(255) NOT NULL,
  service_master_id int(11) NOT NULL,
  fetcher_startedBy varchar(45) DEFAULT NULL,
  parser_startedBy varchar(45) DEFAULT NULL,
  cron_startTime datetime DEFAULT NULL,
  fetcher_startTime datetime DEFAULT NULL,
  fetcher_endTime datetime DEFAULT NULL,
  parser_startTime datetime DEFAULT NULL,
  parser_endTime datetime DEFAULT NULL,
  fetched_file_count int(11) DEFAULT NULL,
  fetched_file_names varchar(45)  DEFAULT NULL,
  message_count int(11) DEFAULT NULL,
  blob_ref_id varchar(45) DEFAULT NULL,
  blob_ref_url varchar(500),
  service_job_status varchar(45)  DEFAULT NULL,
  isTestJob tinyint(1) NOT NULL DEFAULT '0',
  stack_trace varchar(500) ,
  createdBy varchar(45) DEFAULT NULL,
  modifiedBy varchar(45) DEFAULT NULL,
  createdOn datetime DEFAULT NULL,
  modifiedOn datetime DEFAULT NULL,
  PRIMARY KEY (id)
);


DROP TABLE IF EXISTS vertical;
CREATE TABLE vertical (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(100) NOT NULL,
  isActive BOOLEAN NOT NULL DEFAULT '1',
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS service_sample_data;
CREATE TABLE service_sample_data (
id	int(11) AUTO_INCREMENT,
job_id	int(11),
sample_data	varchar(100),
created_on	datetime 
);

DROP TABLE IF EXISTS service_master_history;
CREATE TABLE service_master_history (
`id`	int(11) AUTO_INCREMENT,
`service_master_id` int(11),
`change_json` varchar(500),
`type` varchar(100),
`createdBy`	varchar(45),
`createdOn`	datetime 
);

DROP TABLE IF EXISTS service_schedule_master;
CREATE TABLE service_schedule_master (
  id int(11) NOT NULL AUTO_INCREMENT,
  service_master_id	int(11),
  frequency	varchar(45),
  custom_frequency	varchar(200),
  isActive	int(1),
  timezone	varchar(45),
  startTime	datetime,
  createdBy	varchar(50),
  modifiedBy	varchar(50),
  createdOn	datetime,
  modifiedOn	datetime,
  PRIMARY KEY (id)
);


INSERT INTO category VALUES (1,'EPGS','1',NULL,NULL,NULL),(2,'Relay','1',NULL,NULL,NULL),(3,'Music Chart','1','mapperjson','UDIS_MusicChartInput','UDIS_MusicChartApp'),(4,'Radio Station','1','mapperjson','UDIS_VideoOttIndiaInput','UDIS_VideoOttIndiaApp');

INSERT INTO fetcher_master VALUES (7,'Http_fetcher','Mandatry args','Optional Args]','audiophphttp_fetcher.php','php cli_init.php --args={}',2,'1','amjadhav','amjadhav',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO fetcher_master VALUES (8,'Http_fetcher_test','Mandatry args','Optional Args]','audiophphttp_fetcher.php','php cli_init.php --args={}',2,'1','amjadhav','amjadhav',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO parser_master VALUES (12,'Generic_music_charts_HTML','[{\"gn-chart-id\":\"this the the unique GN id\"}{\"implicitRank\":\"This flag is set as 1 if ranks are not mentioned on the website as a text\"}{\"rankLocation\":\"Locaiton of rank on the website. Dont set this parameter if implicitRank is set to 1.\"}{\"titleLocation\":\"\"}{\"artistLocation\":\"\"}{\"areArtistAndTitleInOnePhrase\":\"\"}{\"sizeOfList\":\"\"}{\"isTextReplacingPositionChangeImage\":\"\"}{\"areListHeadingsPresent\":\"\"}{\"chart-id\":\"\"}]','[{\"characterSeperatingTitleAndArtist\":\"\"}{\"extraWordsInArtist\":\"\"}{\"extraWordsInTitle\":\"\"}{\"textRepresentingPositionChangeImage\":\"\"}{\"whichListToProcess\":\"\"}{\"albumLocation\":\"\"},{\"multipleArtistsName\":\"\"}{\"characterSeperatingMultipleArtistsName\":\"\"}{\"streamsLocation\":\"\"}]','audiphpgeneric_html_music_charts_parser.php','php cli_init.php --args={}','1','adsharma','ansharma',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO parser_master VALUES (13,'Generic_music_charts_HTML','[{\"gn-chart-id\":\"this the the unique GN id\"}{\"implicitRank\":\"This flag is set as 1 if ranks are not mentioned on the website as a text\"}{\"rankLocation\":\"Locaiton of rank on the website. Dont set this parameter if implicitRank is set to 1.\"}{\"titleLocation\":\"\"}{\"artistLocation\":\"\"}{\"areArtistAndTitleInOnePhrase\":\"\"}{\"sizeOfList\":\"\"}{\"isTextReplacingPositionChangeImage\":\"\"}{\"areListHeadingsPresent\":\"\"}{\"chart-id\":\"\"}]','[{\"characterSeperatingTitleAndArtist\":\"\"}{\"extraWordsInArtist\":\"\"}{\"extraWordsInTitle\":\"\"}{\"textRepresentingPositionChangeImage\":\"\"}{\"whichListToProcess\":\"\"}{\"albumLocation\":\"\"},{\"multipleArtistsName\":\"\"}{\"characterSeperatingMultipleArtistsName\":\"\"}{\"streamsLocation\":\"\"}]','audiphpgeneric_html_music_charts_parser.php','php cli_init.php --args={}','1','adsharma','ansharma',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO parser_master VALUES (14,'Generic_music_charts_HTML','[{\"gn-chart-id\":\"this the the unique GN id\"}{\"implicitRank\":\"This flag is set as 1 if ranks are not mentioned on the website as a text\"}{\"rankLocation\":\"Locaiton of rank on the website. Dont set this parameter if implicitRank is set to 1.\"}{\"titleLocation\":\"\"}{\"artistLocation\":\"\"}{\"areArtistAndTitleInOnePhrase\":\"\"}{\"sizeOfList\":\"\"}{\"isTextReplacingPositionChangeImage\":\"\"}{\"areListHeadingsPresent\":\"\"}{\"chart-id\":\"\"}]','[{\"characterSeperatingTitleAndArtist\":\"\"}{\"extraWordsInArtist\":\"\"}{\"extraWordsInTitle\":\"\"}{\"textRepresentingPositionChangeImage\":\"\"}{\"whichListToProcess\":\"\"}{\"albumLocation\":\"\"},{\"multipleArtistsName\":\"\"}{\"characterSeperatingMultipleArtistsName\":\"\"}{\"streamsLocation\":\"\"}]','audiphpgeneric_html_music_charts_parser.php','php cli_init.php --args={}','1','adsharma','ansharma',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO service_master VALUES  (24,'Hot 100 Songs Brazil','hot_100_songs_brazil','2','3','rbhuwad','1','default','FETCHER INITIALIZED','PARSER COMPLETED',NULL,'1','1',1,'admin',CURRENT_TIMESTAMP(),'rbhuwad',CURRENT_TIMESTAMP());
INSERT INTO service_master VALUES  (25,'Hot 100 Songs Brazil','hot_100_songs_brazil','2','3','rbhuwad','1','default','FETCHER INITIALIZED','PARSER COMPLETED',NULL,'1','1',1,'admin',CURRENT_TIMESTAMP(),'rbhuwad',CURRENT_TIMESTAMP());
INSERT INTO service_master VALUES  (26,'Hot 100 Songs Brazil','hot_100_songs_brazil','2','3','rbhuwad','1','default','FETCHER INITIALIZED','PARSER COMPLETED',NULL,'1','1',1,'admin',CURRENT_TIMESTAMP(),'rbhuwad',CURRENT_TIMESTAMP());
INSERT INTO service_master VALUES  (27,'Hot 100 Songs Brazil','hot_100_songs_brazil','2','3','rbhuwad','1','default','FETCHER INITIALIZED','PARSER COMPLETED',NULL,'1','1',1,'admin',CURRENT_TIMESTAMP(),'rbhuwad',CURRENT_TIMESTAMP());

INSERT INTO service_fetcher_detail VALUES (15,24,7,'{\"mandatory\": {\"base_url\": \"http://asdfg-menezes.org/chtsinglesb.html\"}}','admin','admin',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO service_parser_detail VALUES (14,24,12,'{\"optional\": {\"whichListToProcess\": \"0\"}, \"mandatory\": {\"chart-id\": \"hq-630480\", \"sizeOfList\": \"100\", \"gn-chart-id\": \"27000000000097\", \"implicitRank\": \"0\", \"rankLocation\": \"1\", \"titleLocation\": \"4\", \"artistLocation\": \"5\", \"areListHeadingsPresent\": \"1\", \"areArtistAndTitleInOnePhrase\": \"0\", \"isTextReplacingPositionChangeImage\": \"0\"}}','admin','adsharma',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());
INSERT INTO service_parser_detail VALUES (15,26,14,'{\"optional\": {\"whichListToProcess\": \"0\"}, \"mandatory\": {\"chart-id\": \"hq-630480\", \"sizeOfList\": \"100\", \"gn-chart-id\": \"27000000000097\", \"implicitRank\": \"0\", \"rankLocation\": \"1\", \"titleLocation\": \"4\", \"artistLocation\": \"5\", \"areListHeadingsPresent\": \"1\", \"areArtistAndTitleInOnePhrase\": \"0\", \"isTextReplacingPositionChangeImage\": \"0\"}}','admin','adsharma',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());

INSERT INTO vertical VALUES (1,'Other','1'),(2,'Music','1'),(3,'Video','1'),(4,'Sports','1');

INSERT INTO service_notification_detail VALUES (1,24,1,'ALL');

INSERT INTO user_notification_detail VALUES (1,'akshay.pise@nielsen.com');

INSERT INTO service_job_status(id,job_id,service_master_id) values (1,'sampleJobID',24);

INSERT INTO service_sample_data VALUES (1,1,'Sample Dat',CURRENT_TIMESTAMP());

INSERT INTO service_schedule_master VALUES (1,25,'hourly','',1,'IND',CURRENT_TIMESTAMP(),'admin','admin',CURRENT_TIMESTAMP(),CURRENT_TIMESTAMP());








