package br.com.haircutter.core.facade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.service.EstablishmentsQueryService;

@RestController
@RequestMapping(value = "/api/establishmentsQuery")
public class EstablishmentsQueryFacade {

	 Logger LOGGER = LoggerFactory.getLogger(EstablishmentServiceFacade.class);
	 
	 @Autowired
	 private EstablishmentsQueryService establishmentQueryService;
	 
	 @RequestMapping(value = {"/findByText"}, method = RequestMethod.GET)
	 public ResponseEntity<?> findAll(@RequestParam("text") String text, @RequestParam("username") String username) {
		 LOGGER.info("Started by " + username + " - Find establishment by text");
		 
		 List<Establishment> establishments = establishmentQueryService.findAll(text);

	     LOGGER.info("Ended by " + username + " - Find establishment by text");
	     
	     return ResponseEntity.ok(establishments);
	}
}
