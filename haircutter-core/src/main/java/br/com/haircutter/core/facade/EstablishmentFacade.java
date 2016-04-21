package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.service.EstablishmentService;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hfaria on 31/03/16.
 */
@RestController
@RequestMapping(value = "/api/establishment")
public class EstablishmentFacade {

    Logger LOGGER = LoggerFactory.getLogger(EstablishmentFacade.class);

    @Autowired
    EstablishmentService service;

    @RequestMapping(value = {"/{cnpj}/profile"}, method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("cnpj") String cnpj) {

        LOGGER.info("Started - Edit establishment", StructuredArguments.value("cnpj", cnpj));

        Establishment establishment = service.get(cnpj);

        LOGGER.info("Ended - Edit establishment",
                StructuredArguments.value("payload", establishment));

        return ResponseEntity.ok(establishment);
    }

    @RequestMapping(value = {"/profile"}, method = RequestMethod.PUT)
    public ResponseEntity<?> edit(@RequestBody Establishment establishment, @RequestParam("username") String username) {

        LOGGER.info("Started - Edit establishment",
                StructuredArguments.value("payload", establishment));

        service.edit(establishment, username);

        LOGGER.info("Ended - Edit establishment");

        return ResponseEntity.ok().build();
    }

}
