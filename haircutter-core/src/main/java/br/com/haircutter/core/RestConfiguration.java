package br.com.haircutter.core;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.com.haircutter.core.facade.EstablishmentFacade;

@Configuration
@ApplicationPath("/api")
public class RestConfiguration extends ResourceConfig {

	public RestConfiguration() {
		register(EstablishmentFacade.class);

	}

}