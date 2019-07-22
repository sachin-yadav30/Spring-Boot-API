package org.testng.gn;


import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.gn.udip.UDIPSQLConnectMain;
import org.gn.udip.controller.CategoryController;
import org.gn.udip.controller.JobController;
import org.gn.udip.exception.DBException;
import org.gn.udip.model.CategoryModel;
import org.gn.udip.model.FetcherKafkaDetail;
import org.gn.udip.model.ServiceParserDetailResponsePojo;
import org.gn.udip.model.JobStatusEnum;
import org.gn.udip.model.JobStatusModel;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.model.ServiceFetcherDetail;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.model.TriggerFetcherResponseModel;
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
public class TestNgExecutor extends AbstractTestNGSpringContextTests {

	@Autowired
	CategoryController testController;
	
	@Autowired
	JobController jobController;
	
	@Autowired
	DataService dataService;
	
	String jobId ;

	@BeforeClass
	public void init() {
	}

	@AfterClass
	public void destroy() {
	}

	/*Category Controller*/
	
	@Test
	public void testGetCategory() throws Exception {
		/*String expected = "{\"serviceId\":24,\"serviceName\":\"hot_100_songs_brazil\",\"category\":{\"categoryId\":3,\"categoryName\":\"Music Chart\",\"mappingJson\":\"mapperjson\",\"inputBlobType\":\"UDIS_MusicChartInput\",\"outputBlobType\":\"UDIS_MusicChartApp\"},\"fetcherRunStatus\":\"FETCHER COMPLETED\",\"isConcatenatedData\":true}";*/
		ServiceMaster service = testController.getCategoryDetail("24");
		Assert.assertNotNull(service);
		Assert.assertEquals(service.getUniqueId(), "hot_100_songs_brazil");
		Assert.assertEquals(service.getIsConcatenatedData(),new Boolean(true));
		Assert.assertEquals(service.getFetcherRunStatus(),"FETCHER INITIALIZED");
		Assert.assertNotNull(service.getCategory());
		Assert.assertNotNull(service.getCategory().getInputBlobType());
		Assert.assertNotNull(service.getCategory().getMappingJson());
		Assert.assertNotNull(service.getCategory().getOutputBlobType());

	}
	
	@Test(expectedExceptions=NumberFormatException.class)
	public void testCategoryWithNullServiceId() throws Exception {
		testController.getCategoryDetail(null);
	}

	@Test(expectedExceptions=DBException.class)
	public void testCategoryWithInvalidServiceId() throws Exception {
		testController.getCategoryDetail("999999");
	}
	
	
	
	@Test
	public void testGetAllCategory() throws Exception {
		/*String expected = "[{\"inputBlobType\":null,\"outputBlobType\":null,\"mappingJson\":null,\"categoryName\":\"EPGS\",\"categoryId\":1},{\"inputBlobType\":null,\"outputBlobType\":null,\"mappingJson\":null,\"categoryName\":\"Relay\",\"categoryId\":2},{\"inputBlobType\":\"UDIS_MusicChartInput\",\"outputBlobType\":\"UDIS_MusicChartApp\",\"mappingJson\":\"mapperjson\",\"categoryName\":\"Music Chart\",\"categoryId\":3},{\"inputBlobType\":\"UDIS_VideoOttIndiaInput\",\"outputBlobType\":\"UDIS_VideoOttIndiaApp\",\"mappingJson\":\"mapperjson\",\"categoryName\":\"Radio Station\",\"categoryId\":4}]";*/
		List<CategoryModel> catergoryList = testController.getCategory();
		Assert.assertNotNull(catergoryList);
		Assert.assertTrue(catergoryList.size()>0);
		for(CategoryModel c : catergoryList){
			Assert.assertNotNull(c.getCategoryId());
			Assert.assertNotNull(c.getCategoryName());
		}
		
	}
	
	
	
	/*Job Controller*/
	@Test
	public void testCreateNewJob() throws Exception{
		TriggerFetcherResponseModel resp =  jobController.triggerFetcher("24", "1", false);
		Assert.assertNotNull(resp);
		Assert.assertTrue(resp.getRecords().size() > 0);
		Assert.assertNotNull(resp.getRecords().get(0).getValue());
		Assert.assertNotNull(resp.getValue_schema());
		FetcherKafkaDetail k = resp.getRecords().get(0).getValue();
		Assert.assertNotNull(k.getFetcherId());
		Assert.assertNotNull(k.getJobId());
		Assert.assertNotNull(k.getCategory());
		Assert.assertNotNull(k.getFetcherCommand());
		Assert.assertNotNull(k.getServiceId());
		Assert.assertNotNull(k.toString());
		Assert.assertNotNull(k.getBlobtype());
		Assert.assertNotNull(k.getServiceFetcherArgs());
		jobId = resp.getRecords().get(0).getValue().getJobId();
		
		
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testCreateNewJobWithInvalidService() throws Exception{
		try{
			jobController.triggerFetcher("99999", "1", false);
		}catch(DBException e){
			Assert.assertEquals(e.getErrCode(), "404");
			Assert.assertEquals(e.getErrDesc(), "Invalid Service Id");
			throw e;
		}
		
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testCreateNewJobWithAbsenceOfServiceFetcherDetails() throws Exception{
		try{
			jobController.triggerFetcher("27", "1", false);
		}catch(DBException e){
			Assert.assertEquals(e.getErrCode(), "404");
			Assert.assertEquals(e.getErrDesc(), "Invalid Service Id - No such ServiceFetcherDetails found for that particular Service Id in ServiceFetcherDetail table");
			throw e;
		}
		
	}
	
	@Test(expectedExceptions=NumberFormatException.class)
	public void testCreateNewJobWithNullService() throws Exception{
		
		jobController.triggerFetcher(null, "1", false);
		
	}

	@Test 
	public void testUpdateJobStatusForFetcherRunning() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(JobStatusEnum.FETCHER_RUNNING);
		RequestStatus status = jobController.updateJobStatus(req);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(),"Request Successfuly Processed");
	}
	
	@Test 
	public void testUpdateJobStatusForFetcherFailed() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(JobStatusEnum.FETCHER_FAILED);
		RequestStatus status = jobController.updateJobStatus(req);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(),"Request Successfuly Processed");
	}
	
	@Test 
	public void testUpdateJobStatusForFetcherCompleted() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(JobStatusEnum.FETCHER_COMPLETED);
		req.setBlobRefId("xyx");
		req.setBlobRefUrl("gracenotetest.com");
		RequestStatus status = jobController.updateJobStatus(req);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(),"Request Successfuly Processed");
	}
	
	@Test 
	public void testUpdateJobStatusForParserRunning() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(JobStatusEnum.PARSER_RUNNING);
		RequestStatus status = jobController.updateJobStatus(req);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(),"Request Successfuly Processed");
	}
	
	@Test 
	public void testUpdateJobStatusForParserFailed() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(JobStatusEnum.PARSER_FAILED);
		RequestStatus status = jobController.updateJobStatus(req);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(),"Request Successfuly Processed");
	}
	
	@Test 
	public void testUpdateJobStatusForParserCompleted() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(JobStatusEnum.PARSER_COMPLETED);
		RequestStatus status = jobController.updateJobStatus(req);
		Assert.assertEquals(status.getStatusCode(),"00");
		Assert.assertEquals(status.getStatusDesc(),"Request Successfuly Processed");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testUpdateJobStatusWithNullStatus() throws Exception {
		JobStatusModel req = new JobStatusModel();
		req.setJobId(Long.valueOf(jobId));
		req.setServiceJobStatus(null);
		jobController.updateJobStatus(req);
	}
	
	@Test
	public void testSampleJobDataInjester() throws Exception{
		String expected = "{\"statusCode\":00,\"statusDesc\":\"Request Successfuly Processed\"}";
		RequestStatus status = jobController.insertTestRunData(jobId, "sampleData");
		Assert.assertEquals(status.getStatusCode(),"00");
		
	}
	
	@Test(expectedExceptions=DBException.class)
	public void testSampleJobDataInjesterWithInvalidJobId() throws Exception{
		jobController.insertTestRunData("777777", "sampleData");
	}
	
	@Test(expectedExceptions=Exception.class)
	public void testSampleJobDataInjesterWithNullJobId() throws Exception{
		jobController.insertTestRunData(null, "sampleData");
	}
	
	
	@Test 
	public void testGetParserDetails() throws Exception{
		ServiceParserDetailResponsePojo respBean =  jobController.getParserDetail(jobId);
		Assert.assertNotNull(respBean);
		Assert.assertEquals(respBean.getServiceId().toString(),"24");
		Assert.assertEquals(respBean.getParserDetail().getParserName(),"Generic_music_charts_HTML");
		Assert.assertNotNull(respBean.getIsTestJob());
		Assert.assertNotNull(respBean.getParserArgs());
		Assert.assertNotNull(respBean.getMapperId());
		Assert.assertNotNull(respBean.getIsConcatenatedData());
		Assert.assertNotNull(respBean.getParserDetail().getParserCommand());
		Assert.assertNotNull(respBean.getParserDetail().getParserId());
		Assert.assertNotNull(respBean.getParserDetail().getScriptName());
		
	}
	
	@Test(expectedExceptions=DBException.class) 
	public void testGetParserDetailsWithInvalidJobId() throws Exception{
		jobController.getParserDetail("9999");
	
	}
	
	@Test(expectedExceptions=Exception.class) 
	public void testGetParserDetailsWithNullJobId() throws Exception{
		jobController.getParserDetail(null);
	
	}
	
	@Test
	public void testCleaningActivity() throws DBException{
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -3);
		dataService.truncateTestData(cal.getTime());

	}
	
	@Test
	public void testCleaningActivityWithCurrentDate() throws DBException{
		dataService.truncateTestData(Calendar.getInstance().getTime());

	}
	
	@Test(expectedExceptions=DBException.class)
	public void testCleaningActivityWithNullDate() throws DBException{
		
		dataService.truncateTestData(null);

	}
	
	@Test
	public void testGetServiceFetcherDetail() throws DBException{
		
		  ServiceMaster serviceBean=new ServiceMaster();
		  serviceBean.setServiceId(new Long(24));
		  ServiceFetcherDetail bean = dataService.getServiceFetcherDetails(serviceBean);
		  Assert.assertNotNull(bean);
		  Assert.assertNotNull(bean.getFetcherMaster());
		  Assert.assertNotNull(bean.getMapperId());
		  Assert.assertNotNull(bean.getFetcherMaster().getScriptName());
		  Assert.assertNotNull(bean.getFetcherMaster().getFetcherCommand());
		  Assert.assertNotNull(bean.getFetcherMaster().getFetcherId());
	}
	
	@Test
	public void testGetServiceFetcherDetailWithInvalidServiceId() throws DBException{
		
		ServiceMaster serviceBean=new ServiceMaster();
		serviceBean.setServiceId(new Long(99999));
		Assert.assertNull(dataService.getServiceFetcherDetails(serviceBean));

	}
	
	@Test
	public void testGetServiceFetcherDetailWithNullServiceId() throws DBException{
		
		Assert.assertNull(dataService.getServiceFetcherDetails(null));

	}
	
}
