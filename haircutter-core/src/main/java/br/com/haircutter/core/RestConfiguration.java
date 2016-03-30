package br.com.haircutter.core;

import br.com.haircutter.core.facade.EstablishmentAuditLogFacade;
import br.com.haircutter.core.facade.EstablishmentFacade;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import javax.ws.rs.ApplicationPath;

@Configuration
@ApplicationPath("/api")
public class RestConfiguration extends ResourceConfig {

    public RestConfiguration() {
        register(EstablishmentFacade.class);
        register(EstablishmentAuditLogFacade.class);
    }

}