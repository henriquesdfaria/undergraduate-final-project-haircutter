package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.ProfessionalCalendar;
import br.com.haircutter.core.service.ProfessionalCalendarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/professional")
public class ProfessionalCalendarFacade {

    Logger LOGGER = LoggerFactory.getLogger(ProfessionalCalendarFacade.class);

    @Autowired
    ProfessionalCalendarService professionalCalendarService;

    @RequestMapping(value = {"/calendar"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProfessionalCalendar professionalCalendar, @RequestParam("cnpj") String cnpj, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Create Professional Calendar Schedule", professionalCalendar);

        ProfessionalCalendar createdProfessionalCalendar = professionalCalendarService.create(professionalCalendar, cnpj, username);

        LOGGER.info("Ended by " + username + " - Create Professional Calendar Schedule", createdProfessionalCalendar);

        return ResponseEntity.ok(createdProfessionalCalendar);
    }


    @RequestMapping(value = {"/calendar/{professionalCalendarId}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("professionalCalendarId") Long professionalCalendarId, @RequestParam("cnpj") String cnpj, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Delete Professional Calendar Schedule", id);

        professionalCalendarService.delete(professionalCalendarId, cnpj, username);

        LOGGER.info("Ended by " + username + " - Edit Professional Calendar Schedule", id);

    }

    @RequestMapping(value = {"/{establishmentEmployeeId}/calendars"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishmentEmployeeId(@PathVariable("establishmentEmployeeId") Long establishmentEmployeeId) {

        LOGGER.info("Started - Get Professional Calendar Schedules by establishmentEmployeeId", id);

        List<ProfessionalCalendar> professionalCalendars = professionalCalendarService.getAllByEstablishmentEmployeeId(establishmentEmployeeId);

        LOGGER.info("Ended - Get Professional Calendar Schedules by establishmentEmployeeId", professionalCalendars);

        return ResponseEntity.ok(professionalCalendars);
    }


}
