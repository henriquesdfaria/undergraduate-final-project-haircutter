package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.ComplaintJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hfaria on 6/22/16.
 */
@Component
public class ComplaintEndpoint {
    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();


    public Object getAll() {
        return restTemplate.getForObject(API_URL + "/complaints", Object.class);
    }

    public void resolve(Long complaintId) {
        restTemplate.put(API_URL + "/complaint/" + complaintId + "?username="
                + LoggedUserUtils.getLoggedUserUsername(), complaintId);
    }

    public Object create(final ComplaintJson complaintJson) {
        complaintJson.setUsername(LoggedUserUtils.getLoggedUserUsername());

        return restTemplate.postForObject(API_URL + "/complaint", complaintJson, Object.class);
    }
}
