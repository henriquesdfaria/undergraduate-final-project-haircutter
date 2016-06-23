package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentEvaluation;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface EstablishmentEvaluationRepository extends CrudRepository<EstablishmentEvaluation, Long> {

}
