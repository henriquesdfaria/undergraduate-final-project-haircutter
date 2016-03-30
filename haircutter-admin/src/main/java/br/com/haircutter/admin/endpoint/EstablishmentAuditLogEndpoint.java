package br.com.haircutter.admin.endpoint;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentAuditLogEndpoint {

	@Value("${endpoint.haircutter.core.api.url}")
	private String API_URL;

	private RestTemplate restTemplate = new RestTemplate();

	public Object getAuditLogsByCnpj(String cnpj) {

		return restTemplate.getForObject(API_URL + "/establishment-audit-log/audit-logs/" + cnpj, Object.class);
	}
}
