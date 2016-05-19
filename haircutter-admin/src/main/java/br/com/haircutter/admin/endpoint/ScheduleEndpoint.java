package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.ScheduleJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ScheduleEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object create(ScheduleJson scheduleJson) {

        return restTemplate.postForObject(API_URL + "/schedule?author=" + LoggedUserUtils.getLoggedUserUsername(),
                scheduleJson, Object.class);
    }

    public Object getAllByUsername() {
        return restTemplate.getForObject(API_URL + "/schedule/username?username=" +
                LoggedUserUtils.getLoggedUserUsername(), Object.class);
    }

    public Object getAllByProfessional(Long professionalId) {
        return restTemplate.getForObject(API_URL + "/schedule/professional/" + professionalId, Object.class);
    }

    public void cancel(Long scheduleId) {
        restTemplate.delete(API_URL + "/schedule/" + scheduleId + "?author=" +
                LoggedUserUtils.getLoggedUserUsername());
    }
}
