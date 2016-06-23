package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.EstablishmentEvaluationComentary;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface EstablishmentEvaluationComentaryRepository extends CrudRepository<EstablishmentEvaluationComentary, Long> {

}
