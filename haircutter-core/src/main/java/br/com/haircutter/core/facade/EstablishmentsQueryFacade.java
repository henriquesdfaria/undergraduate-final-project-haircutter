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
@RequestMapping(value = "/api/establishments-query")
public class EstablishmentsQueryFacade {
	
	 Logger LOGGER = LoggerFactory.getLogger(ProfessionalCalendarFacade.class);
	 
	 @Autowired
	 EstablishmentsQueryService establishmentsQueryService;
	 
	 @RequestMapping(value = {"/find-all"}, method = RequestMethod.GET)
	 public ResponseEntity<?> findAll(@RequestParam("username") String username, @RequestParam("city") String city){
		 
		 LOGGER.info("Started by " + username + " - Establishments query");
		 
		 List<Establishment> establishments = establishmentsQueryService.findByCity(city);
		 
		 LOGGER.info("Ended by " + username + " - Establishments query");
		 
		 return ResponseEntity.ok(establishments);
	 }
	
}
