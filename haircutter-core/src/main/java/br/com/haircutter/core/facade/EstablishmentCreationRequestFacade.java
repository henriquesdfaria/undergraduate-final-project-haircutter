package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.service.EstablishmentCreationRequestService;
import net.logstash.logback.argument.StructuredArguments;
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
@RequestMapping(value = "/api/establishment")
public class EstablishmentCreationRequestFacade {

	Logger LOGGER = LoggerFactory.getLogger(EstablishmentCreationRequestFacade.class);

	@Autowired
	EstablishmentCreationRequestService service;

	@RequestMapping(value = {"/creation-request"}, method = RequestMethod.POST)
	public ResponseEntity<?> create(Establishment establishment) {

		LOGGER.info("Started - New Establishment Creation Request",
				StructuredArguments.value("payload", establishment));

		Establishment createdEstablishment = service.create(establishment);

		LOGGER.info("Ended - New Establishment Creation Request",
				StructuredArguments.value("payload", createdEstablishment));

        return ResponseEntity.ok(createdEstablishment);
	}

	@RequestMapping(value = {"/creation-requests"}, method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {

		List<Establishment> establishments = service.getAll();

        return ResponseEntity.ok(establishments);
	}

	@RequestMapping(value = {"/creation-request/approve/{cnpj}"}, method = RequestMethod.PUT)
	public ResponseEntity<?> approve(@PathVariable("cnpj") String cnpj) {

		LOGGER.info("Started - Approve Creation Request",
				StructuredArguments.value("payload", cnpj));

		service.approve(cnpj);
		
		LOGGER.info("Ended - Approve Creation Request",
				StructuredArguments.value("payload", null));

        return ResponseEntity.ok().build();
	}

	@RequestMapping(value = {"/creation-request/deny/{cnpj}"}, method = RequestMethod.PUT)
	public ResponseEntity<?> deny(@PathVariable("cnpj") String cnpj) {

		LOGGER.info("Started - Deny Creation Request",
				StructuredArguments.value("payload", cnpj));

		service.deny(cnpj);
		
		LOGGER.info("Ended - Deny Creation Request",
				StructuredArguments.value("payload", null));

        return ResponseEntity.ok().build();
	}

}
