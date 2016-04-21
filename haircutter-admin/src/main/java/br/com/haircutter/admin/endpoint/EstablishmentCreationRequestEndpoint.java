package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.EstablishmentJson;
import org.springframework.beans.factory.annotation.Value;
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

    public void approveCreationRequest(final String cnpj) {
        restTemplate.put(API_URL + "/establishment/creation-request/approve/" + cnpj, null);
    }

    public void denyCreationRequest(final EstablishmentJson establishmentJson) {
        restTemplate.put(API_URL + "/establishment/creation-request/deny", establishmentJson);
    }

}
