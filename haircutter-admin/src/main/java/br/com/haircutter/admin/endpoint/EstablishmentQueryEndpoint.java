package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentQueryEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object findAllByCity(String city) {

        return restTemplate.getForObject(API_URL + "/establishments-query/find-all?username="
                + LoggedUserUtils.getLoggedUserUsername() + "&city=" + city, Object.class);
    }

}
