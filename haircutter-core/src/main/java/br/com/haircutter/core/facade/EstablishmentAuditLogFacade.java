package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.EstablishmentAuditLog;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/establishment-audit-log")
@Consumes("application/json")
@Produces("application/json")
public class EstablishmentAuditLogFacade {

	Logger LOGGER = LoggerFactory.getLogger(EstablishmentAuditLogFacade.class);

	@Autowired
	EstablishmentAuditLogService service;

	@GET
	@Path("/audit-logs/{cnpj}")
	public Response getAuditLogsByCnpj(@PathParam("cnpj") String cnpj) {

		LOGGER.info("Started - Get Audit Logs By CNPJ", cnpj);

		List<EstablishmentAuditLog> responseBody = service.getAuditLogsByCnpj(cnpj);

		LOGGER.info("Ended - Get Audit Logs By CNPJ", responseBody);

		return Response.ok(responseBody).build();
	}

}
