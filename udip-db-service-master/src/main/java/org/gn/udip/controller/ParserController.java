package org.gn.udip.controller;

import org.apache.log4j.Logger;
import org.gn.udip.model.ParserMasterPojo;
import org.gn.udip.model.RequestStatus;
import org.gn.udip.service.ParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parser")
public class ParserController {
	
	@Autowired
	ParserService parserService;

	private static Logger logger = Logger.getLogger(ParserController.class);
	
	@PostMapping("/add")
	public RequestStatus addParserData(@RequestBody String parserDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : INSERT PARSER DATA : " + parserDataReq);
		parserService.addParserData(parserDataReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/update")
	public RequestStatus updateParserData(@RequestBody String parserDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : UPDATE PARSER DATA : " + parserDataReq);
		parserService.updateParserData(parserDataReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}
	
	@PostMapping("/delete")
	public RequestStatus deleteParserData(@RequestBody ParserMasterPojo parserDataReq) throws Exception {

		logger.info("REQUEST ARRIVED : DELETE PARSER DATA HAVING PARSER ID : " + parserDataReq.getParserId());
		parserService.deleteParserData(parserDataReq);

		return new RequestStatus("00", "Request Successfuly Processed");
	}

}
