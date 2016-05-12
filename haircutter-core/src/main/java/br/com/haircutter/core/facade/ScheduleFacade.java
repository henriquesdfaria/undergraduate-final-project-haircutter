package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.Schedule;
import br.com.haircutter.core.service.ScheduleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/schedule")
public class ScheduleFacade {

    Logger LOGGER = LoggerFactory.getLogger(ScheduleFacade.class);

    @Autowired
    ScheduleService scheduleService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Schedule schedule, @RequestParam("username") String username) {

        LOGGER.info("Started - Register schedule", schedule);

        Schedule createdSchedule = scheduleService.create(schedule.getProfessionalServiceId(), schedule.getUsername(),
                schedule.getScheduleDate(), schedule.getScheduleInMinutes(), username);

        LOGGER.info("Ended - Register schedule", createdSchedule);

        return ResponseEntity.ok(createdSchedule);
    }

    @RequestMapping(value = "/{scheduleId}", method = RequestMethod.DELETE)
    public void cancel(@PathVariable("scheduleId") Long scheduleId, @RequestParam("username") String username) {

        LOGGER.info("Started - Cancel Schedule", username);

        scheduleService.cancel(scheduleId, username);

        LOGGER.info("Ended - Cancel Schedule");
    }


    @RequestMapping(value = "/professional/{establishmentEmployeeId}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByProfessional(@PathVariable("establishmentEmployeeId") Long establishmentEmployeeId) {

        LOGGER.info("Started - Get all schedules by professional", establishmentEmployeeId);

        List<Schedule> schedules = scheduleService.getAllByProfessional(establishmentEmployeeId);

        LOGGER.info("Ended - Get all schedules by professional", schedules);

        return ResponseEntity.ok(schedules);
    }

    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public ResponseEntity<?> getAllByProfessional(@RequestParam("username") String username) {

        LOGGER.info("Started - Get all schedules by username", username);

        List<Schedule> schedules = scheduleService.getAllByUsername(username);

        LOGGER.info("Ended - Get all schedules by username", schedules);

        return ResponseEntity.ok(schedules);
    }

}
