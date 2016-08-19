package com.healthint.documentreceiver;

import javax.inject.Inject;

import org.apache.log4j.Logger;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.client.RestOperations;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.healthint.documentreceiver.services.ServicesURI;

import healthint.integration.RequestMedicalActivity;
import healthint.integration.RequestMedicalActivityResponse;
import healthint.integration.Status;

@Endpoint
public class WSMedicProceduresEndpoint {
	private static final String NAMESPACE_URI = "http://healthint/integration";

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

		restTemplate.getForObject(ServicesURI.getAdministrationURI(request), String.class);

		Boolean approvedByHealthPlan = restTemplate
				.getForObject(ServicesURI.getHealthPlanURI(procedure, request.getHealthplan()), boolean.class);
		if (approvedByHealthPlan) {
			String materials = restTemplate.getForObject(ServicesURI.getMaterialsURI(procedure, request.getMaterials()),
					String.class);
			status.setMaterialsAvailable(materials);
			String facilities = restTemplate.getForObject(ServicesURI.getFacilitiesURI(procedure), String.class);
			status.setRoomNumber(facilities);
			String expenses = restTemplate.getForObject(ServicesURI.getExpensesURI(procedure), String.class);
			status.setTotalExpenses(expenses);
		}

		RequestMedicalActivityResponse resp = new RequestMedicalActivityResponse();
		status.setApprovedByHealthPlanRet(approvedByHealthPlan);
		resp.setStatusInfo(status);
		return resp;
	}
}