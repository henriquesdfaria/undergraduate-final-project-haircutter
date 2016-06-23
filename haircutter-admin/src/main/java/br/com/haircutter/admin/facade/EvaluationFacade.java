package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EvaluationEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentEvaluationJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class EvaluationFacade {

	@Autowired
	private EvaluationEndpoint evaluationEndpoint;

	@RequestMapping(value = "/client/establishment/evaluate", method = RequestMethod.POST)
	public ResponseEntity<?> evaluate(@RequestBody EstablishmentEvaluationJson establishmentEvaluationJson) {
		return ResponseEntity.ok(evaluationEndpoint.evaluate(establishmentEvaluationJson));
	}

}
