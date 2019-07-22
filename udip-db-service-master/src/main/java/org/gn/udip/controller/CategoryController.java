package org.gn.udip.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.gn.udip.model.CategoryModel;
import org.gn.udip.model.ServiceMaster;
import org.gn.udip.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CategoryController {
	
	
	private static Logger logger=Logger.getLogger(CategoryController.class);
	
	@Autowired
	DataService dataService;
	
	@GetMapping("/category")
    public ServiceMaster getCategoryDetail(@RequestParam("serviceId") String serviceId ) throws Exception {
		
		logger.info("REQUEST ARRIVED : GET CATEGORY DETAILS :Service ID :"+serviceId);
		return dataService.getService(Long.valueOf(serviceId));
	}
	
	@GetMapping("/category/get")
    public List<CategoryModel> getCategory() throws Exception {
		
		logger.info("REQUEST ARRIVED : GET CATEGORY GETALL");
		return dataService.getCategory();
	}
}
