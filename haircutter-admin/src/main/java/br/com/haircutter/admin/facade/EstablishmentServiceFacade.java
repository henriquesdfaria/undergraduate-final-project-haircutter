package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentServiceEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentServiceJson;
import br.com.haircutter.admin.service.EstablishmentEmployeeUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 4/24/16.
 */
@RestController
@RequestMapping(value = "/api")
public class EstablishmentServiceFacade {

    @Autowired
    EstablishmentEmployeeUserService establishmentEmployeeUserService;

    @Autowired
    EstablishmentServiceEndpoint establishmentServiceEndpoint;

    @RequestMapping(value = {"/manager/establishment/service"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody EstablishmentServiceJson establishmentServiceJson) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        establishmentServiceJson.setEstablishmentCnpj(cnpj);

        return ResponseEntity.ok(establishmentServiceEndpoint.create(establishmentServiceJson));
    }

    @RequestMapping(value = {"/manager/establishment/service"}, method = RequestMethod.PUT)
    public void edit(@RequestBody EstablishmentServiceJson establishmentServiceJson) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        establishmentServiceEndpoint.edit(establishmentServiceJson, cnpj);
    }

    @RequestMapping(value = {"/manager/establishment/service/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        establishmentServiceEndpoint.delete(id, cnpj);
    }

    @RequestMapping(value = {"/manager/establishment/service/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("id") Long id) {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(establishmentServiceEndpoint.getByIdAndCnpj(id, cnpj));
    }

    @RequestMapping(value = {"/manager/establishment/services"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCnpj() {

        String cnpj = establishmentEmployeeUserService.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(establishmentServiceEndpoint.getAllByCnpj(cnpj));
    }
}
