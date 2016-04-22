package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentJson;
import br.com.haircutter.admin.service.EstablishmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hfaria on 07/04/16.
 */
@RestController
@RequestMapping(value = "/api")
public class EstablishmentFacade {

    @Autowired
    private EstablishmentEndpoint endpoint;


    @Autowired
    private EstablishmentUserService service;


    @RequestMapping(value = {"/establishment-admin/establishment/profile"}, method = RequestMethod.GET)
    public ResponseEntity<?> get() {

        String cnpj = service.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(endpoint.get(cnpj));
    }

    @RequestMapping(value = {"/establishment-admin/establishment/profile"}, method = RequestMethod.PUT)
    public void edit(@RequestBody EstablishmentJson establishmentJson) {

        endpoint.edit(establishmentJson);
    }

    @RequestMapping(value = "/establishment-admin/establishment/deactivate", method = RequestMethod.DELETE)
    public void deactivate() {
        String cnpj = service.getCnpjByLoggedUserUsername();

        endpoint.deactivate(cnpj);
    }
}
