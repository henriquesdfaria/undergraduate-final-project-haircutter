package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class EstablishmentFacade {

    @Autowired
    private EstablishmentEndpoint endpoint;

    @RequestMapping(value = {"/public/new-establishment-creation-request"}, method = RequestMethod.POST)
    public ResponseEntity<?> createNewRequest(@RequestBody EstablishmentJson establishmentJson) {

        return ResponseEntity.ok(endpoint.createNewRequest(establishmentJson));
    }

    @RequestMapping(value = {"/moderator/establishment-creation-requests"}, method = RequestMethod.GET)
    public ResponseEntity<?> creationRequests() {

        return ResponseEntity.ok(endpoint.getCreationRequests());
    }

    @RequestMapping(value = {"/moderator/establishment-creation-request/{cnpj}"}, method = RequestMethod.GET)
    public ResponseEntity<?> creationRequest(@PathVariable("cnpj") String cnpj) {

        return ResponseEntity.ok(endpoint.getCreationRequest(cnpj));
    }

    @RequestMapping(value = "/moderator/approve-establishment-creation-request/{cnpj}", method = RequestMethod.PUT)
    public ResponseEntity<?> approveCreationRequest(@PathVariable("cnpj") String cnpj) {

        return ResponseEntity.ok(endpoint.approveCreationRequest(cnpj));
    }

    @RequestMapping(value = "/moderator/deny-establishment-creation-request/{cnpj}", method = RequestMethod.PUT)
    public ResponseEntity<?> denyCreationRequest(@PathVariable("cnpj") String cnpj) {

        return ResponseEntity.ok(endpoint.denyCreationRequest(cnpj));
    }

}
