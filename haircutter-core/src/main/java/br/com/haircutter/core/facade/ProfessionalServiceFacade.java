package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.ProfessionalService;
import br.com.haircutter.core.service.ProfessionalServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/professional")
public class ProfessionalServiceFacade {

    Logger LOGGER = LoggerFactory.getLogger(ProfessionalServiceFacade.class);

    @Autowired
    ProfessionalServiceService professionalServiceService;

    @RequestMapping(value = {"/service"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody ProfessionalService professionalService, @RequestParam("cnpj") String cnpj, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Associate Service to professional", professionalService);

        ProfessionalService createdProfessionalService = professionalServiceService.create(professionalService, cnpj, username);

        LOGGER.info("Ended by " + username + " - Associate Service to professional", createdProfessionalService);

        return ResponseEntity.ok(createdProfessionalService);
    }


    @RequestMapping(value = {"/service/{professionalServiceId}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("professionalServiceId") Long professionalServiceId, @RequestParam("cnpj") String cnpj, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Edit Establishment EstablishmentService", id);

        professionalServiceService.delete(professionalServiceId, cnpj, username);

        LOGGER.info("Ended by " + username + " - Edit Establishment EstablishmentService", id);

    }

    @RequestMapping(value = {"/{establishmentEmployeeId}/services"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishmentEmployeeId(@PathVariable("establishmentEmployeeId") Long establishmentEmployeeId) {

        LOGGER.info("Started - Get Professional Services by establishmentEmployeeId", id);

        List<ProfessionalService> professionalServices = professionalServiceService.getAllByEstablishmentEmployeeId(establishmentEmployeeId);

        LOGGER.info("Ended - Get Establishment Services by establishmentEmployeeId", professionalServices);

        return ResponseEntity.ok(professionalServices);
    }


}
