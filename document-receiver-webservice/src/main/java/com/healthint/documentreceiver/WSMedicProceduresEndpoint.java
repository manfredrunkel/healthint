package com.healthint.documentreceiver;

import java.io.StringWriter;
import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestOperations;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import healthint.integration.RequestMedicalActivity;
import healthint.integration.RequestMedicalActivityResponse;
import healthint.integration.Status;

@Endpoint
public class WSMedicProceduresEndpoint {
	private static final String NAMESPACE_URI = "http://healthint/integration";

	private static String EXPENSES_SERVICE = "http://erp-integration-expenses-service/calculateExpenses";
	private static String MATERIALS_SERVICE = "http://erp-integration-materials-service/registerMaterials";
	private static String FACILITIES_SERVICE = "http://erp-integration-facilities-service/registerFacilitiesUsage";
	private static String HEALTHPLAN_SERVICE = "http://health-plan-validator-service/validateProcedure";
	private static String ADMINISTRATION_SERVICE = "http://administration-service/registerDoc";

	final static Logger logger = Logger.getLogger(WSMedicProceduresEndpoint.class);

	@Inject
	@LoadBalanced
	private RestOperations restTemplate;

	public WSMedicProceduresEndpoint() {
	}

	/**
	 * Will check against health plan if procedure is allowed. If yes, it will
	 * check if materials are available, room and calculate expenses forecast
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "requestMedicalActivity")
	@ResponsePayload
	public RequestMedicalActivityResponse requestMedicalActivity(@RequestPayload RequestMedicalActivity request) {
		String procedure = request.getProcedure();
		Status status = new Status();

		logger.info("Received Document for requestMedicalActivity");
		logger.info("Procedure: " + request.getProcedure());
		logger.info("HealthPlan: " + request.getHealthplan());
		logger.info("Materials:" + request.getMaterials());

		restTemplate.getForObject(getAdministrationURI(request), String.class);

		Boolean approvedByHealthPlan = restTemplate.getForObject(getHealthPlanURI(procedure, request.getHealthplan()),
				boolean.class);
		if (approvedByHealthPlan) {
			String materials = restTemplate.getForObject(getMaterialsURI(procedure, request.getMaterials()),
					String.class);
			status.setMaterialsAvailable(materials);
			String facilities = restTemplate.getForObject(getFacilitiesURI(procedure), String.class);
			status.setRoomNumber(facilities);
			String expenses = restTemplate.getForObject(getExpensesURI(procedure), String.class);
			status.setTotalExpenses(expenses);
		}

		RequestMedicalActivityResponse resp = new RequestMedicalActivityResponse();
		status.setApprovedByHealthPlanRet(approvedByHealthPlan);
		resp.setStatusInfo(status);
		return resp;
	}

	private URI getAdministrationURI(RequestMedicalActivity request) {
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
		logger.info("Encoded Document" + encoded);
		URI uri = UriComponentsBuilder.fromUriString(ADMINISTRATION_SERVICE).queryParam("document", encoded).build()
				.toUri();
		return uri;
	}

	private URI getHealthPlanURI(String medicProcedure, String healthPlan) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("medicProcedure", medicProcedure);
		params.add("healthplan", healthPlan);
		URI uri = UriComponentsBuilder.fromUriString(HEALTHPLAN_SERVICE).queryParams(params).build().toUri();
		return uri;
	}

	private URI getMaterialsURI(String medicProcedure, List<String> components) {
		String componentsCommaSeparated = StringUtils.join(components, ",");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("medicProcedure", medicProcedure);
		params.add("components", componentsCommaSeparated);

		URI uri = UriComponentsBuilder.fromUriString(MATERIALS_SERVICE).queryParams(params).build().toUri();
		return uri;
	}

	private URI getFacilitiesURI(String medicProcedure) {
		URI uri = UriComponentsBuilder.fromUriString(FACILITIES_SERVICE).queryParam("medicProcedure", medicProcedure)
				.build().toUri();
		return uri;
	}

	private URI getExpensesURI(String medicProcedure) {
		URI uri = UriComponentsBuilder.fromUriString(EXPENSES_SERVICE).queryParam("medicProcedure", medicProcedure)
				.build().toUri();
		return uri;
	}
}