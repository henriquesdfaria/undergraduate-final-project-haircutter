package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentCreationRequestEndpoint;
import br.com.haircutter.admin.facade.json.EstablishmentJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class EstablishmentCreationRequestFacade {

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
    public void approveCreationRequest(@PathVariable("cnpj") String cnpj) {
        endpoint.approveCreationRequest(cnpj);
    }

    @RequestMapping(value = "/moderator/establishment/creation-request/deny", method = RequestMethod.PUT)
    public void denyCreationRequest(@RequestBody EstablishmentJson establishmentJson) {
        endpoint.denyCreationRequest(establishmentJson);
    }

}
