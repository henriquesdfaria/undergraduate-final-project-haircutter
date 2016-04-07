package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentAuditLogEndpoint;
import br.com.haircutter.admin.service.EstablishmentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class EstablishmentAuditLogFacade {

    @Autowired
    private EstablishmentAuditLogEndpoint endpoint;

    @Autowired
    private EstablishmentUserService service;

    @RequestMapping(value = {"/establishment-admin/establishment-audit-log/audit-logs"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAuditLogsByCnpj() {

        String cnpj = service.getCnpjByLoggedUserUsername();

        return ResponseEntity.ok(endpoint.getAuditLogsByCnpj(cnpj));
    }
}
