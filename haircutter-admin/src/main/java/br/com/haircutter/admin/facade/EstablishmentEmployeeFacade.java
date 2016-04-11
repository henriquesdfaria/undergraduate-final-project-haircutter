package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentEmployeeEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentEmployeeJson;
import br.com.haircutter.admin.service.EstablishmentAdminUserService;
import br.com.haircutter.admin.service.EstablishmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 07/04/16.
 */
@RestController
@RequestMapping(value = "/api")
public class EstablishmentEmployeeFacade {

    @Autowired
    private EstablishmentEmployeeEndpoint endpoint;

    @Autowired
    private EstablishmentAdminUserService establishmentAdminUserService;


    @Autowired
    private EstablishmentUserService establishmentUserService;


    @RequestMapping(value = {"/establishment-admin/employees"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishment() {

        String cnpj = establishmentUserService.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(endpoint.getAllByEstablishment(cnpj));
    }

    @RequestMapping(value = {"/establishment-admin/employee/{employeeId}"}, method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("employeeId") Long employeeId) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(employeeId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(endpoint.get(employeeId));
    }

    @RequestMapping(value = {"/establishment-admin/employees/profile"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody EstablishmentEmployeeJson establishmentEmployeeJson) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(establishmentEmployeeJson.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(endpoint.create(establishmentEmployeeJson));
    }

    @RequestMapping(value = {"/establishment-admin/employees/profile"}, method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@RequestBody EstablishmentEmployeeJson establishmentEmployeeJson) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(establishmentEmployeeJson.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        endpoint.edit(establishmentEmployeeJson);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/establishment-admin/employee/{employeeId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("employeeId") Long employeeId) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(employeeId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        endpoint.delete(employeeId);

        return ResponseEntity.ok().build();
    }
}
