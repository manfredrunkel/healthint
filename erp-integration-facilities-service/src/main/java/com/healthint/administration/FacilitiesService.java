package com.healthint.administration;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FacilitiesService {

	final static Logger logger = Logger.getLogger(FacilitiesService.class);

	@RequestMapping("/registerFacilitiesUsage")
	public String registerFacilitiesUsage(String medicProcedure) {
		// call ERP Integration
		logger.info("Registering Facilities usage for Medic Procedure:" + medicProcedure);
		logger.info("Schedule 1 Room for procedure:" + medicProcedure);
		return "102";
	}

}
