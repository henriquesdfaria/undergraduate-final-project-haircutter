package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ScheduleEndpoint;
import br.com.haircutter.admin.facade.json.ScheduleJson;
import br.com.haircutter.admin.service.EstablishmentEmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 4/24/16.
 */
@RestController
@RequestMapping(value = "/api")
public class ScheduleFacade {

    @Autowired
    EstablishmentEmployeeUserService establishmentEmployeeUserService;

    @Autowired
    ScheduleEndpoint scheduleEndpoint;

    @RequestMapping(value = {"/client/schedule", "/professional/schedule", "/attendant/schedule", "/manager/schedule"},
            method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ScheduleJson scheduleJson) {

        return ResponseEntity.ok(scheduleEndpoint.create(scheduleJson));
    }

    @RequestMapping(value = {"/client/schedule/{scheduleId}", "/professional/schedule/{scheduleId}",
            "/attendant/schedule/{scheduleId}", "/manager/schedule/{scheduleId}"}, method = RequestMethod.DELETE)
    public void cancel(@PathVariable("scheduleId") Long scheduleId) {

        scheduleEndpoint.cancel(scheduleId);
    }

    @RequestMapping(value = {"/public/schedule/professional/{establishmentEmployeeId}"},
            method = RequestMethod.POST)
    public ResponseEntity<?> getAllByProfessional(@PathVariable("establishmentEmployeeId") Long establishmentEmployeeId) {

        return ResponseEntity.ok(scheduleEndpoint.getAllByProfessional(establishmentEmployeeId));
    }

    @RequestMapping(value = {"/professional/schedule/professional/{establishmentEmployeeId}"},
            method = RequestMethod.POST)
    public ResponseEntity<?> professionalGetAllByProfessional(@PathVariable("establishmentEmployeeId") Long establishmentEmployeeId) {

        establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername();

        return ResponseEntity.ok(scheduleEndpoint.getAllByProfessional(establishmentEmployeeId));
    }

    @RequestMapping(value = {"/public/schedule/username"}, method = RequestMethod.POST)
    public ResponseEntity<?> getAllByUsername() {

        return ResponseEntity.ok(scheduleEndpoint.getAllByUsername());
    }

}
