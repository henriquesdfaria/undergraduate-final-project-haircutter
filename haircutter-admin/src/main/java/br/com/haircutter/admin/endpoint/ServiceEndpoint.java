package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.ServiceJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ServiceEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(ServiceJson serviceJson) {

        return restTemplate.postForObject(API_URL + "/establishment/service?username="
                + LoggedUserUtils.getLoggedUserUsername(), serviceJson, Object.class);
    }

    public void edit(ServiceJson serviceJson, String cnpj) {

        restTemplate.put(API_URL + "/establishment/" + cnpj + "/service?username=" + LoggedUserUtils.getLoggedUserUsername(),
                serviceJson);
    }

    public void delete(Long id, String cnpj) {
        restTemplate.delete(API_URL + "/establishment/" + cnpj + "/service/" + id + "?" + LoggedUserUtils.getLoggedUserUsername());
    }

    public Object getByIdAndCnpj(Long id, String cnpj) {
        return restTemplate.getForObject(API_URL + "/establishment/" + cnpj + "/service/" + id, null, Object.class);
    }

    public Object getAllByCnpj(String cnpj) {
        return restTemplate.getForObject(API_URL + "/establishment/" + cnpj + "/services", null, Object.class);
    }
}
