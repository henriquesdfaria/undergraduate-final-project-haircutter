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

/**
 * Created by hfaria on 31/03/16.
 */
@Component
@Path("/establishment")
@Consumes("application/json")
@Produces("application/json")
public class EstablishmentFacade {

    Logger LOGGER = LoggerFactory.getLogger(EstablishmentFacade.class);

    @Autowired
    EstablishmentService service;

    @GET
    @Path("/{cnpj}/profile")
    public Response get(@PathParam("cnpj") String cnpj) {

        LOGGER.info("Started - Edit establishment", StructuredArguments.value("cnpj", cnpj));

        Establishment responseBody = service.get(cnpj);

        LOGGER.info("Ended - Edit establishment",
                StructuredArguments.value("payload", responseBody));

        return Response.ok(responseBody).build();
    }

    @PUT
    @Path("/profile")
    public Response edit(Establishment establishment, @QueryParam("username") String username) {

        LOGGER.info("Started - Edit establishment",
                StructuredArguments.value("payload", establishment));

        service.edit(establishment, username);

        LOGGER.info("Ended - Edit establishment");

        return Response.ok().build();
    }

}
