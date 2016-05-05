package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentEmployeeEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentEmployeeJson;
import br.com.haircutter.admin.service.EstablishmentAdminUserService;
import br.com.haircutter.admin.service.EstablishmentEmployeeUserService;
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
    private EstablishmentEmployeeEndpoint establishmentEmployeeEndpoint;

    @Autowired
    private EstablishmentAdminUserService establishmentAdminUserService;

    @Autowired
    private EstablishmentEmployeeUserService establishmentEmployeeUserService;

    @RequestMapping(value = {"/establishment-admin/employees"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishment() {

        String cnpj = establishmentAdminUserService.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(establishmentEmployeeEndpoint.getAllByEstablishment(cnpj));
    }

    @RequestMapping(value = {"/establishment-admin/employee/{employeeId}"}, method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("employeeId") Long employeeId) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(employeeId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(establishmentEmployeeEndpoint.get(employeeId));
    }

    @RequestMapping(value = {"/establishment-admin/employee"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody EstablishmentEmployeeJson establishmentEmployeeJson) {

        establishmentEmployeeJson.setEstablishmentCnpj(establishmentAdminUserService.getCnpjByLoggedUserUsername());

        return ResponseEntity.ok(establishmentEmployeeEndpoint.create(establishmentEmployeeJson));
    }

    @RequestMapping(value = {"/establishment-admin/employee"}, method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@RequestBody EstablishmentEmployeeJson establishmentEmployeeJson) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(establishmentEmployeeJson.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        establishmentEmployeeEndpoint.edit(establishmentEmployeeJson);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/establishment-admin/employee/{employeeId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("employeeId") Long employeeId) {

        if (!establishmentAdminUserService.verifyEmployeeIsInOwnEstablishment(employeeId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        establishmentEmployeeEndpoint.delete(employeeId);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/professional/profile"}, method = RequestMethod.GET)
    public ResponseEntity<?> getProfessionalProfile() {

        Long id = establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername();

        return ResponseEntity.ok(establishmentEmployeeEndpoint.get(id));
    }

    @RequestMapping(value = {"/professional/profile"}, method = RequestMethod.PUT)
    public ResponseEntity<?> editProfile(@RequestBody EstablishmentEmployeeJson establishmentEmployeeJson) {

        Long id = establishmentEmployeeUserService.getEmployeeIdByLoggedUserUsername();

        establishmentEmployeeJson.setId(id);

        establishmentEmployeeEndpoint.edit(establishmentEmployeeJson);

        return ResponseEntity.ok().build();
    }


}
