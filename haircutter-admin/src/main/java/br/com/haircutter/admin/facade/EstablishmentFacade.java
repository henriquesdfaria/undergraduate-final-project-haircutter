package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentCreationRequestEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class EstablishmentFacade {

    @Autowired
    private EstablishmentCreationRequestEndpoint endpoint;

    @RequestMapping(value = {"/public/establishment/creation-request"}, method = RequestMethod.POST)
    public ResponseEntity<?> createNewRequest(@RequestBody EstablishmentJson establishmentJson) {

        return ResponseEntity.ok(endpoint.create(establishmentJson));
    }

    @RequestMapping(value = {"/moderator/establishment/creation-requests"}, method = RequestMethod.GET)
    public ResponseEntity<?> creationRequests() {

        return ResponseEntity.ok(endpoint.getAll());
    }

    @RequestMapping(value = "/moderator/establishment/creation-request/approve/{cnpj}", method = RequestMethod.PUT)
    public ResponseEntity<?> approveCreationRequest(@PathVariable("cnpj") String cnpj) {

        return ResponseEntity.ok(endpoint.approveCreationRequest(cnpj));
    }

    @RequestMapping(value = "/moderator/establishment/creation-request/deny/{cnpj}", method = RequestMethod.PUT)
    public ResponseEntity<?> denyCreationRequest(@PathVariable("cnpj") String cnpj) {

        return ResponseEntity.ok(endpoint.denyCreationRequest(cnpj));
    }

}
