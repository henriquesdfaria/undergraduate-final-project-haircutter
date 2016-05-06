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
