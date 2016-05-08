package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.UserJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ClientEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(UserJson userJson) {

        return restTemplate.postForObject(API_URL + "/client", userJson, Object.class);
    }

    public Object get() {
        return restTemplate.getForObject(API_URL + "/client/" + LoggedUserUtils.getLoggedUserUsername(), Object.class);
    }

    public void edit(UserJson userJson) {
        restTemplate.put(API_URL + "/client", userJson);
    }

    public void delete() {
        restTemplate.delete(API_URL + "/client/" + LoggedUserUtils.getLoggedUserUsername());
    }
}
