package br.com.haircutter.core.service.impl;


import br.com.haircutter.core.model.EstablishmentEvaluation;
import br.com.haircutter.core.model.repository.EstablishmentEvaluationRepository;
import br.com.haircutter.core.service.EvaluationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

@Service
public class EvaluationServiceImpl implements EvaluationService {

    @Autowired
    EstablishmentEvaluationRepository establishmentEvaluationRepository;

    @Override
    public EstablishmentEvaluation evaluate(EstablishmentEvaluation establishmentEvaluation) {
        establishmentEvaluation.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        establishmentEvaluation.setCreationTime(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        establishmentEvaluation.setStatus("OK");
        return establishmentEvaluationRepository.save(establishmentEvaluation);
    }
}
