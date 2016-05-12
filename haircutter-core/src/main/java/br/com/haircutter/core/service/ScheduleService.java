package br.com.haircutter.core.service;

import br.com.haircutter.core.model.Schedule;

import java.util.Date;
import java.util.List;

public interface ScheduleService {

    Schedule create(Long professionalServiceId, String username, Date scheduleDate, Integer scheduleInMinutes,
                    String author);

    void cancel(Long scheduleId, String author);

    List<Schedule> getAllByProfessional(Long establishmentEmployeeId);

    List<Schedule> getAllByUsername(String username);

}
