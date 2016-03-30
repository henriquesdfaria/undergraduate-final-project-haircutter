package br.com.haircutter.admin.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import br.com.haircutter.admin.facade.json.EstablishmentJson;

@Component
public class EstablishmentEndpoint {

	@Value("${endpoint.haircutter.core.api.url}")
	private String API_URL;

	private RestTemplate restTemplate = new RestTemplate();

	public Object createNewRequest(final EstablishmentJson establishmentJson) {

		return restTemplate.postForObject(API_URL + "/establishment/create-new-request", establishmentJson,
				Object.class);
	}

	public Object getCreationRequests() {

		return restTemplate.getForObject(API_URL + "/establishment/creation-requests", Object.class);
	}

	public Object getCreationRequest(final String cnpj) {

		return restTemplate.getForObject(API_URL + "/establishment/creation-request/" + cnpj, Object.class);
	}

	public Object approveCreationRequest(final String cnpj) {

		return restTemplate.exchange(API_URL + "/establishment/approve-creation-request/" + cnpj, HttpMethod.PUT, null,
				Object.class);
	}

	public Object denyCreationRequest(final String cnpj) {

		return restTemplate.exchange(API_URL + "/establishment/deny-creation-request/" + cnpj, HttpMethod.PUT, null,
				Object.class);
	}

	public Object getAuditLogsByCnpj(String cnpj) {

		return restTemplate.getForObject(API_URL + "/establishment/audit-logs/" + cnpj, Object.class);
	}
}
