package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ServiceEndpoint;
import br.com.haircutter.admin.facade.json.ServiceJson;
import br.com.haircutter.admin.service.EstablishmentEmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 4/24/16.
 */
@RestController
@RequestMapping(value = "/api")
public class ServiceFacade {

    @Autowired
    EstablishmentEmployeeUserService establishmentEmployeeUserService;

    @Autowired
    ServiceEndpoint serviceEndpoint;

    @RequestMapping(value = {"/manager/establishment/service"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ServiceJson serviceJson) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        serviceJson.setEstablishmentCnpj(cnpj);

        return ResponseEntity.ok(serviceEndpoint.create(serviceJson));
    }

    @RequestMapping(value = {"/manager/establishment/service"}, method = RequestMethod.PUT)
    public void edit(@RequestBody ServiceJson serviceJson) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        serviceEndpoint.edit(serviceJson, cnpj);
    }

    @RequestMapping(value = {"/manager/establishment/service/{id}"}, method = RequestMethod.POST)
    public void delete(@PathVariable("id") Long id) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        serviceEndpoint.delete(id, cnpj);
    }

    @RequestMapping(value = {"/manager/establishment/service/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(serviceEndpoint.getByIdAndCnpj(id, cnpj));
    }

    @RequestMapping(value = {"/manager/establishment/services"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCnpj() {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(serviceEndpoint.getAllByCnpj(cnpj));
    }
}
