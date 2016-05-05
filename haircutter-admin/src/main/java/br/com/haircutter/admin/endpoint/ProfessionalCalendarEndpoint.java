package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.ProfessionalCalendarJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProfessionalCalendarEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(ProfessionalCalendarJson professionalCalendarJson, String cnpj) {
        return restTemplate.postForObject(API_URL + "/professional/calendar?username="
                + LoggedUserUtils.getLoggedUserUsername() + "&cnpj=" + cnpj, professionalCalendarJson, Object.class);
    }

    public void delete(Long professionalCalendarId, String cnpj) {
        restTemplate.delete(API_URL + "/professional/calendar/" + professionalCalendarId + "?username="
                + LoggedUserUtils.getLoggedUserUsername() + "&cnpj=" + cnpj);
    }

    public Object getAllByEstablishmentEmployeeId(Long professionalEmployeeId) {
        return restTemplate.getForObject(API_URL + "/professional/" + professionalEmployeeId + "/calendars", Object.class);
    }
}
