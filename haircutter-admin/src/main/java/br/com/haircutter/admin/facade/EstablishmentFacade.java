package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentJson;
import br.com.haircutter.admin.utils.LoggedUserUtils;
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

    @RequestMapping(value = {"/establishment-admin/establishment/edit"}, method = RequestMethod.POST)
    public ResponseEntity<?> createNewRequest(@RequestBody EstablishmentJson establishmentJson) {

        return ResponseEntity.ok(endpoint.edit(establishmentJson));
    }
}
