package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.model.ProfessionalCalendar;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ProfessionalCalendarRespository extends CrudRepository<ProfessionalCalendar, Long> {

    List<ProfessionalCalendar> findAllByEstablishmentEmployeeId(Long establishmentEmployeeId);
}
