package com.healthint.administration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MaterialsService {

	final static Logger logger = Logger.getLogger(MaterialsService.class);

	@RequestMapping("/checkMaterialsExistence")
	public String checkMaterialsExistence(String materials) {
		// TO-DO
		return "{quantity:1}";
	}
	
	
	@RequestMapping("/registerMaterials")
	public String registerMaterials(String medicProcedure, String components) {
		// call ERP Integration
		String[] materials = components.split(",");
		List<String> materialsRequested = new ArrayList<String>(Arrays.asList(materials));
		List<String> materialsAvailable = getMaterialsAvailable();
		materialsRequested.retainAll(materialsAvailable);
		logger.info("Available Materials for medic procedure:" + medicProcedure + " : " + materialsRequested);
		return materialsRequested.toString();
	}

	private List<String> getMaterialsAvailable() {
		List<String> materials = new ArrayList<String>();
		materials.add("2003");
		materials.add("55589");
		materials.add("22584");
		return materials;
	}

}
