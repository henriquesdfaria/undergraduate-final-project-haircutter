package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.EstablishmentEvaluation;
import br.com.haircutter.core.service.EvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class EvaluationFacade {

    Logger LOGGER = LoggerFactory.getLogger(EvaluationFacade.class);

    @Autowired
    EvaluationService evaluationService;

    @RequestMapping( value = "/establishment/evaluate", method = RequestMethod.POST)
    public ResponseEntity<?> evaluate(@RequestBody EstablishmentEvaluation establishmentEvaluation) {

        LOGGER.info("Started - Evaluate establishment", establishmentEvaluation);

        EstablishmentEvaluation createdEstablishmentEvaluation = evaluationService.evaluate(establishmentEvaluation);

        LOGGER.info("Ended - Evaluate establishment", createdEstablishmentEvaluation);

        return ResponseEntity.ok(createdEstablishmentEvaluation);
    }
}
