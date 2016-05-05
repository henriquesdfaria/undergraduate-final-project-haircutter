package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.Weekday;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface WeekdayRespository extends CrudRepository<Weekday, Long> {

}
