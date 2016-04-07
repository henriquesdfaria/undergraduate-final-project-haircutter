package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.EstablishmentJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentCreationRequestEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(final EstablishmentJson establishmentJson) {

        return restTemplate.postForObject(API_URL + "/establishment/creation-request", establishmentJson,
                Object.class);
    }

    public Object getAll() {

        return restTemplate.getForObject(API_URL + "/establishment/creation-requests", Object.class);
    }

    public Object approveCreationRequest(final String cnpj) {

        return restTemplate.exchange(
                API_URL + "/establishment/creation-request/approve/" + cnpj,
                HttpMethod.PUT,
                null,
                Object.class);
    }

    public Object denyCreationRequest(final String cnpj) {

        return restTemplate.exchange(
                API_URL + "/establishment/creation-request/deny/" + cnpj,
                HttpMethod.PUT, null,
                Object.class);
    }

}
