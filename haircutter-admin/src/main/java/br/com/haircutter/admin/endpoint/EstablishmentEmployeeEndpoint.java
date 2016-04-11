package br.com.haircutter.admin.endpoint;


import br.com.haircutter.admin.facade.json.EstablishmentEmployeeJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class EstablishmentEmployeeEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object getAllByEstablishment(String cnpj) {

        return restTemplate.getForObject(API_URL + "/establishment-employees/establishment/" + cnpj, Object.class);
    }

    public Object get(Long employeeId) {

        return restTemplate.getForObject(API_URL + "/establishment-employees/employee/" + employeeId, Object.class);
    }

    public Object create(final EstablishmentEmployeeJson establishmentEmployeeJson) {

        return restTemplate.postForObject(API_URL + "/establishment-employees/employee?username="
                + LoggedUserUtils.getLoggedUserUsername(), establishmentEmployeeJson, Object.class);
    }

    public void edit(final EstablishmentEmployeeJson establishmentEmployeeJson) {

        restTemplate.put(API_URL + "/establishment-employees/employee?username="
                + LoggedUserUtils.getLoggedUserUsername(), establishmentEmployeeJson);
    }

    public void delete(Long employeeId) {

        restTemplate.delete(API_URL + "/establishment-employees/employee/" + employeeId
                + "?username=" + LoggedUserUtils.getLoggedUserUsername());
    }

}
