package br.com.haircutter.admin.endpoint;

import br.com.haircutter.admin.facade.json.EstablishmentEvaluationJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by hfaria on 6/22/16.
 */
@Component
public class EvaluationEndpoint {

    @Value("${endpoint.haircutter.core.api.url}")
    private String API_URL;

    private RestTemplate restTemplate = new RestTemplate();

    public Object evaluate(final EstablishmentEvaluationJson establishmentEvaluationJson) {

        return restTemplate.postForObject(API_URL + "/establishment/evaluate?username="
                + LoggedUserUtils.getLoggedUserUsername(), establishmentEvaluationJson, Object.class);
    }

}
