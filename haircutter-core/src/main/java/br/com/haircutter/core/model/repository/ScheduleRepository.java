package br.com.haircutter.core.model.repository;

import br.com.haircutter.core.enums.ScheduleStatusEnum;
import br.com.haircutter.core.model.Schedule;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface ScheduleRepository extends CrudRepository<Schedule, Long> {

    List<Schedule> findAllByProfessionalServiceId(Long professionalServiceId);

    List<Schedule> findAllByUsername(String username);

    Schedule findOneByIdAndUsername(Long id, String username);
    
    List<Schedule> findAllByUsernameAndStatus(String username, ScheduleStatusEnum status);
}
