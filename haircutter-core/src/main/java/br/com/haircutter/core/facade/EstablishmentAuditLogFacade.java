package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.EstablishmentAuditLog;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/establishment-audit-log")
public class EstablishmentAuditLogFacade {

	Logger LOGGER = LoggerFactory.getLogger(EstablishmentAuditLogFacade.class);

	@Autowired
	EstablishmentAuditLogService service;

	@RequestMapping(value = {"/audit-logs/{cnpj}"}, method = RequestMethod.GET)
	public ResponseEntity<?> getAuditLogsByCnpj(@PathVariable("cnpj") String cnpj) {

		LOGGER.info("Started - Get Audit Logs By CNPJ", cnpj);

		List<EstablishmentAuditLog> auditLogs = service.getAuditLogsByCnpj(cnpj);

		LOGGER.info("Ended - Get Audit Logs By CNPJ", auditLogs);

        return ResponseEntity.ok(auditLogs);
	}

}
