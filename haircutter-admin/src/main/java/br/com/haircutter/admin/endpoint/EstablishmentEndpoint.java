package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.EstablishmentJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object edit(final EstablishmentJson establishmentJson) {

        return restTemplate.exchange(
                API_URL + "/establishment/edit?username=" + LoggedUserUtils.getLoggedUserUsername(), HttpMethod.PUT,
                null,
                Object.class, establishmentJson);
    }

}
