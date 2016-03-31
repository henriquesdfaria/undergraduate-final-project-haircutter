package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.service.EstablishmentService;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Component
@Path("/establishment")
@Consumes("application/json")
@Produces("application/json")
public class EstablishmentCreationRequestFacade {

	Logger LOGGER = LoggerFactory.getLogger(EstablishmentCreationRequestFacade.class);

	@Autowired
	EstablishmentService service;

	@POST
	@Path("/creation-request")
	public Response create(Establishment establishment) {

		LOGGER.info("Started - New Establishment Creation Request",
				StructuredArguments.value("payload", establishment));

		Establishment responseBody = service.createNewRequest(establishment);

		LOGGER.info("Ended - New Establishment Creation Request",
				StructuredArguments.value("payload", responseBody));
		
		return Response.ok(responseBody).build();
	}

	@GET
	@Path("/creation-requests")
	public Response getAll() {

		List<Establishment> responseBody = service.getCreationRequests();

		return Response.ok(responseBody).build();
	}

	@PUT
	@Path("/creation-request/approve/{cnpj}")
	public Response approve(@PathParam("cnpj") String cnpj) {

		LOGGER.info("Started - Approve Creation Request",
				StructuredArguments.value("payload", cnpj));

		service.approveCreationRequest(cnpj);
		
		LOGGER.info("Ended - Approve Creation Request",
				StructuredArguments.value("payload", null));

		return Response.ok().build();
	}

	@PUT
	@Path("/creation-request/deny/{cnpj}")
	public Response deny(@PathParam("cnpj") String cnpj) {

		LOGGER.info("Started - Deny Creation Request",
				StructuredArguments.value("payload", cnpj));

		service.denyCreationRequest(cnpj);
		
		LOGGER.info("Ended - Deny Creation Request",
				StructuredArguments.value("payload", null));

		return Response.ok().build();
	}

}
