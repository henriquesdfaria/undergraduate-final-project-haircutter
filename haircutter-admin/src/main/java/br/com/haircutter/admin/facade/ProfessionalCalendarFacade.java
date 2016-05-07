package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ProfessionalCalendarEndpoint;
import br.com.haircutter.admin.facade.json.ProfessionalCalendarJson;
import br.com.haircutter.admin.service.EstablishmentEmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 4/24/16.
 */
@RestController
@RequestMapping(value = "/api")
public class ProfessionalCalendarFacade {

    private static final Integer SERVICE_MINIMUM_DURATON_TIME_IN_MINUTES = 30;

    @Autowired
    EstablishmentEmployeeUserService establishmentEmployeeUserService;

    @Autowired
    ProfessionalCalendarEndpoint professionalCalendarEndpoint;

    @RequestMapping(value = {"/professional/calendar"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProfessionalCalendarJson professionalCalendarJson) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        professionalCalendarJson.setEstablishmentEmployeeId(establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername());

        return ResponseEntity.ok(professionalCalendarEndpoint.create(professionalCalendarJson, cnpj));
    }

    @RequestMapping(value = "/professional/calendars", method = RequestMethod.POST)
    public void createRange(@RequestBody ProfessionalCalendarJson professionalCalendarJson) {
        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        int init = professionalCalendarJson.getScheduleInMinutesFrom();
        int limit = professionalCalendarJson.getScheduleInMinutesTo();

        professionalCalendarJson.setEstablishmentEmployeeId(establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername());

        for (int i = init; i <= limit; i = i + SERVICE_MINIMUM_DURATON_TIME_IN_MINUTES) {
            professionalCalendarJson.setScheduleInMinutes(i);

            try {
                professionalCalendarEndpoint.create(professionalCalendarJson, cnpj);
            } catch (Exception e) {

            }
        }

    }

    @RequestMapping(value = {"/professional/calendar/{professionalCalendarId}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("professionalCalendarId") Long professionalCalendarId) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        professionalCalendarEndpoint.delete(professionalCalendarId, cnpj);
    }


    @RequestMapping(value = {"/professional/calendars"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishmentEmployeeId() {

        Long establishmentEmployeeId = establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername();

        return ResponseEntity.ok(professionalCalendarEndpoint.getAllByEstablishmentEmployeeId(establishmentEmployeeId));
    }
}
