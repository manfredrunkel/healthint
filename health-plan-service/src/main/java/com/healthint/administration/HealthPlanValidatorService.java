package com.healthint.administration;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthPlanValidatorService {

	final static Logger logger = Logger.getLogger(HealthPlanValidatorService.class);

	@RequestMapping("/validateProcedure")
	public boolean registerMaterials(String medicProcedure, String healthplan) {
		boolean accepted = false;
		logger.info("Procedure:" + medicProcedure);
		logger.info("HealthPlan:" + healthplan);
		// FRENECTOMIA
		if (medicProcedure.equals("04.01.01.008-2")) {
			accepted = true;
			logger.info("Accepted!");
		}
		return accepted;
	}

}
