package br.com.haircutter.core.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.haircutter.core.exception.resource.FieldErrorReturn;
import net.logstash.logback.argument.StructuredArguments;

public class CustomInvalidException extends WebApplicationException {

	Logger LOGGER = LoggerFactory.getLogger(CustomInvalidException.class);
	
	private static final long serialVersionUID = 1L;

	private static final String BAD_REQUEST = "Bad Request";

	public CustomInvalidException(String message, String field) {
		super(Response.status(Status.BAD_REQUEST)
				.entity(new FieldErrorReturn(Status.BAD_REQUEST.getStatusCode(), BAD_REQUEST, message, field))
				.type(MediaType.APPLICATION_JSON).build());
		LOGGER.info("Ocorreu um erro", StructuredArguments.value("payload", message));
	}

	public CustomInvalidException(String message) {
		super(message);
		LOGGER.info("Ocorreu um erro", StructuredArguments.value("payload", message));
	}

}
