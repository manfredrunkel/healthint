package com.healthint.administration;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.json.XML;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdministrationService {

	private static final String GET_DOCS_QUERY = "SELECT * FROM DOCUMENTS";
	private static final String INSERT_DOCS_QUERY = "INSERT INTO DOCUMENTS (document,receivedDate) values (?,?)";

	@RequestMapping(method = RequestMethod.GET, value = "/registerDoc")
	public String registerDoc(String document) {
		byte[] decode = Base64.decodeBase64(document);
		String documentDecoded = new String(decode);

		JdbcTemplate template = new JdbcTemplate(Application.getDb());
		Object[] params = new Object[] { documentDecoded, new Date() };
		int[] types = new int[] { Types.CLOB, Types.TIMESTAMP };
		int update = template.update(INSERT_DOCS_QUERY, params, types);

		return update == 1 ? "success" : "fail";
	}

	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET, value = "/getAllDocs")
	public @ResponseBody List<Document> getAllDocs() {
		JdbcTemplate template = new JdbcTemplate(Application.getDb());
		List<Map<String, Object>> queryForList = template.queryForList(GET_DOCS_QUERY);

		List<Document> documents = new ArrayList<Document>();

		for (Map<String, Object> result : queryForList) {
			String xmlDocument = (String) result.get("document");
			String jsonDocument = XML.toJSONObject(xmlDocument).toString();

			Date receivedDate = (Date) result.get("receivedDate");

			documents.add(new Document(jsonDocument, receivedDate));
		}

		return documents;
	}
}
