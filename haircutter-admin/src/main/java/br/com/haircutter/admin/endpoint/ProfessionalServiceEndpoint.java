package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.ProfessionalServiceJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProfessionalServiceEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(ProfessionalServiceJson professionalServiceJson, String cnpj) {

        return restTemplate.postForObject(API_URL + "/professional/service?username="
                + LoggedUserUtils.getLoggedUserUsername() + "&cnpj=" + cnpj, professionalServiceJson, Object.class);
    }


    public void delete(Long professionalServiceId, String cnpj) {
        restTemplate.delete(API_URL + "/professional/service/" + professionalServiceId + "?username="
                + LoggedUserUtils.getLoggedUserUsername() + "&cnpj=" + cnpj);
    }

    public Object getAllByEstablishmentEmployeeId(Long professionalEmployeeId) {
        return restTemplate.getForObject(API_URL + "/professional/" + professionalEmployeeId + "/services", Object.class);
    }
}
