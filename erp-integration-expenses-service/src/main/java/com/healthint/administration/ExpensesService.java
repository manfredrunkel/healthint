package com.healthint.administration;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExpensesService {

	final static Logger logger = Logger.getLogger(ExpensesService.class);

	@RequestMapping("/calculateExpenses")
	public String registerMaterials(String medicProcedure) {
		Double totalValue = 0.0;

		logger.info("Calculating expenses for Medic Procedure: " + medicProcedure);

		if (medicProcedure.equals("04.01.01.008-2")) {
			totalValue = 1232.91;
		} else if (medicProcedure.equals("04.01.01.008-1")) {
			totalValue = 1400.90;
		}

		return String.valueOf(totalValue);
	}

}
