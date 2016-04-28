package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.EstablishmentService;
import br.com.haircutter.core.service.EstablishmentServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/establishment")
public class EstablishmentServiceFacade {

    Logger LOGGER = LoggerFactory.getLogger(EstablishmentServiceFacade.class);

    @Autowired
    EstablishmentServiceService establishmentServiceService;

    @RequestMapping(value = {"/service"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody EstablishmentService establishmentService, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Create EstablishmentService For Establishment", establishmentService);

        EstablishmentService createdEstablishmentService = establishmentServiceService.create(establishmentService, username);

        LOGGER.info("Ended by " + username + " - Create EstablishmentService For Establishment", createdEstablishmentService);

        return ResponseEntity.ok(createdEstablishmentService);
    }

    @RequestMapping(value = {"/{cnpj}/service"}, method = RequestMethod.PUT)
    public void edit(@PathVariable("cnpj") String cnpj, @RequestBody EstablishmentService establishmentService, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Edit Establishment EstablishmentService", establishmentService);

        establishmentServiceService.edit(establishmentService, username, cnpj);

        LOGGER.info("Ended by " + username + " - Edit Establishment EstablishmentService");

    }

    @RequestMapping(value = {"/{cnpj}/service/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("cnpj") String cnpj, @PathVariable("id") Long id, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Edit Establishment EstablishmentService", id);

        establishmentServiceService.delete(id, cnpj, username);

        LOGGER.info("Ended by " + username + " - Edit Establishment EstablishmentService", id);

    }

    @RequestMapping(value = {"/{cnpj}/service/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("cnpj") String cnpj,@PathVariable("id") Long id) {

        LOGGER.info("Started - Get Establishment EstablishmentService by ID", id);

        EstablishmentService establishmentService = establishmentServiceService.getByIdAndCnpj(id, cnpj);

        LOGGER.info("Ended - Get Establishment EstablishmentService by ID", establishmentService);

        return ResponseEntity.ok(establishmentService);
    }

    @RequestMapping(value = {"/{cnpj}/services"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCnpj(@PathVariable("cnpj") String cnpj) {

        LOGGER.info("Started - Get Establishment Services by CNPJ", id);

        List<EstablishmentService> establishmentServices = establishmentServiceService.getAllByCnpj(cnpj);

        LOGGER.info("Ended - Get Establishment Services by CNPJ", establishmentServices);

        return ResponseEntity.ok(establishmentServices);
    }


}
