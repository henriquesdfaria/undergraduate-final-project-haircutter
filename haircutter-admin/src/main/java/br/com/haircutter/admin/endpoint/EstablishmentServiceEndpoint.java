package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.EstablishmentServiceJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentServiceEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(EstablishmentServiceJson establishmentServiceJson) {

        return restTemplate.postForObject(API_URL + "/establishment/service?username="
                + LoggedUserUtils.getLoggedUserUsername(), establishmentServiceJson, Object.class);
    }

    public void edit(EstablishmentServiceJson establishmentServiceJson, String cnpj) {

        restTemplate.put(API_URL + "/establishment/" + cnpj + "/service?username=" + LoggedUserUtils.getLoggedUserUsername(),
                establishmentServiceJson);
    }

    public void delete(Long id, String cnpj) {
        restTemplate.delete(API_URL + "/establishment/" + cnpj + "/service/" + id + "?username=" + LoggedUserUtils.getLoggedUserUsername());
    }

    public Object getByIdAndCnpj(Long id, String cnpj) {
        return restTemplate.getForObject(API_URL + "/establishment/" + cnpj + "/service/" + id, Object.class);
    }

    public Object getAllByCnpj(String cnpj) {
        return restTemplate.getForObject(API_URL + "/establishment/" + cnpj + "/services", Object.class);
    }
}
