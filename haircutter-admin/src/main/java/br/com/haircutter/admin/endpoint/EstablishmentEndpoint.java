package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.EstablishmentJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object get(String cnpj) {

        return restTemplate.getForObject(API_URL + "/establishment/" + cnpj + "/profile", Object.class);
    }

    public void edit(final EstablishmentJson establishmentJson) {

        restTemplate.put(API_URL + "/establishment/profile?username=" + LoggedUserUtils.getLoggedUserUsername(),
                establishmentJson);
    }

}
