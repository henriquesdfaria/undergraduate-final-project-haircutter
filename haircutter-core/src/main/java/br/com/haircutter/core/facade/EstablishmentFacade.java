package br.com.haircutter.core.facade;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.EstablishmentAuditLog;
import br.com.haircutter.core.service.EstablishmentService;
import net.logstash.logback.argument.StructuredArguments;

@Component
@Path("/establishment")
@Consumes("application/json")
@Produces("application/json")
public class EstablishmentFacade {

	Logger LOGGER = LoggerFactory.getLogger(EstablishmentFacade.class);

	@Autowired
	EstablishmentService service;

	@POST
	@Path("/create-new-request")
	public Response submitToCreation(Establishment establishment) {

		LOGGER.info("Started - New Establishment Creation Request",
				StructuredArguments.value("payload", establishment));

		Establishment responseBody = service.createNewRequest(establishment);

		LOGGER.info("Ended - New Establishment Creation Request",
				StructuredArguments.value("payload", responseBody));
		
		return Response.ok(responseBody).build();
	}

	@GET
	@Path("/creation-requests")
	public Response getCreationRequests() {

		List<Establishment> responseBody = service.getCreationRequests();

		return Response.ok(responseBody).build();
	}

	@GET
	@Path("/creation-request/{cnpj}")
	public Response getEstablishmentByCnpj(@PathParam("cnpj") String cnpj) {

		Establishment responseBody = service.getCreationRequestByCnpj(cnpj);

		return Response.ok(responseBody).build();
	}

	@PUT
	@Path("/approve-creation-request/{cnpj}")
	public Response approveCreationRequest(@PathParam("cnpj") String cnpj) {

		LOGGER.info("Started - Approve Creation Request",
				StructuredArguments.value("payload", cnpj));
		
		Establishment responseBody = service.approveCreationRequest(cnpj);
		
		LOGGER.info("Ended - Approve Creation Request",
				StructuredArguments.value("payload", responseBody));

		return Response.ok(responseBody).build();
	}

	@PUT
	@Path("/deny-creation-request/{cnpj}")
	public Response denyCreationRequest(@PathParam("cnpj") String cnpj) {

		LOGGER.info("Started - Deny Creation Request",
				StructuredArguments.value("payload", cnpj));
		
		Establishment responseBody = service.denyCreationRequest(cnpj);
		
		LOGGER.info("Ended - Deny Creation Request",
				StructuredArguments.value("payload", responseBody));

		return Response.ok(responseBody).build();
	}

	@GET
	@Path("audit-logs/{cnpj}")
	public Response getAuditLogsByCnpj(@PathParam("cnpj") String cnpj) {

		List<EstablishmentAuditLog> responseBody = service.getAuditLogsByCnpj(cnpj);

		return Response.ok(responseBody).build();
	}

}
