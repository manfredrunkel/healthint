package com.healthint.documentreceiver.services;

import java.io.StringWriter;
import java.net.URI;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import healthint.integration.RequestMedicalActivity;

public final class ServicesURI {
	
	private static final String EXPENSES_SERVICE = "http://erp-integration-expenses-service/calculateExpenses";
	private static final String MATERIALS_SERVICE = "http://erp-integration-materials-service/registerMaterials";
	private static final String FACILITIES_SERVICE = "http://erp-integration-facilities-service/registerFacilitiesUsage";
	private static final String HEALTHPLAN_SERVICE = "http://health-plan-validator-service/validateProcedure";
	private static final String ADMINISTRATION_SERVICE = "http://administration-service/registerDoc";
	
	public static URI getAdministrationURI(RequestMedicalActivity request) {
		String xmlString = "empty";
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(RequestMedicalActivity.class);
			Marshaller marshaller = context.createMarshaller();
			StringWriter sw = new StringWriter();
			marshaller.marshal(request, sw);
			xmlString = sw.toString();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		String encoded = new String(org.apache.commons.codec.binary.Base64.encodeBase64URLSafe(xmlString.getBytes()));
		URI uri = UriComponentsBuilder.fromUriString(ADMINISTRATION_SERVICE).queryParam("document", encoded).build()
				.toUri();
		return uri;
	}

	public static URI getHealthPlanURI(String medicProcedure, String healthPlan) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("medicProcedure", medicProcedure);
		params.add("healthplan", healthPlan);
		URI uri = UriComponentsBuilder.fromUriString(HEALTHPLAN_SERVICE).queryParams(params).build().toUri();
		return uri;
	}

	public static URI getMaterialsURI(String medicProcedure, List<String> components) {
		String componentsCommaSeparated = StringUtils.join(components, ",");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("medicProcedure", medicProcedure);
		params.add("components", componentsCommaSeparated);

		URI uri = UriComponentsBuilder.fromUriString(MATERIALS_SERVICE).queryParams(params).build().toUri();
		return uri;
	}

	public static URI getFacilitiesURI(String medicProcedure) {
		URI uri = UriComponentsBuilder.fromUriString(FACILITIES_SERVICE).queryParam("medicProcedure", medicProcedure)
				.build().toUri();
		return uri;
	}

	public static URI getExpensesURI(String medicProcedure) {
		URI uri = UriComponentsBuilder.fromUriString(EXPENSES_SERVICE).queryParam("medicProcedure", medicProcedure)
				.build().toUri();
		return uri;
	}

}
