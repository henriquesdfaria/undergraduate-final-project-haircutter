package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ProfessionalServiceEndpoint;
import br.com.haircutter.admin.facade.json.ProfessionalServiceJson;
import br.com.haircutter.admin.service.EstablishmentEmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 4/24/16.
 */
@RestController
@RequestMapping(value = "/api")
public class ProfessionalServiceFacade {

    @Autowired
    EstablishmentEmployeeUserService establishmentEmployeeUserService;

    @Autowired
    ProfessionalServiceEndpoint professionalServiceEndpoint;

    @RequestMapping(value = {"/professional/service"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProfessionalServiceJson professionalServiceJson) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        professionalServiceJson.setEstablishmentEmployeeId(establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername());

        return ResponseEntity.ok(professionalServiceEndpoint.create(professionalServiceJson, cnpj));
    }

    @RequestMapping(value = {"/professional/service/{professionalServiceId}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("professionalServiceId") Long professionalServiceId) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        professionalServiceEndpoint.delete(professionalServiceId, cnpj);
    }


    @RequestMapping(value = {"/professional/services"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishmentEmployeeId() {

        Long establishmentEmployeeId = establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername();

        return ResponseEntity.ok(professionalServiceEndpoint.getAllByEstablishmentEmployeeId(establishmentEmployeeId));
    }
}
