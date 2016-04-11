package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.EstablishmentEmployee;
import br.com.haircutter.core.service.EstablishmentEmployeeService;
import net.logstash.logback.argument.StructuredArguments;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hfaria on 31/03/16.
 */
@RestController
@RequestMapping(value = "/api/establishment-employees")
public class EstablishmentEmployeeFacade {

    Logger LOGGER = LoggerFactory.getLogger(EstablishmentEmployeeFacade.class);

    @Autowired
    EstablishmentEmployeeService service;

    @RequestMapping(value = {"/establishment/{cnpj}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByEstablishment(@PathVariable("cnpj") String cnpj) {

        LOGGER.info("Started - Get all establishment employees by establishment", StructuredArguments.value("cnpj", cnpj));

        List<EstablishmentEmployee> establishmentEmployees = service.getAllByEstablishment(cnpj);

        LOGGER.info("Ended - Get all establishment employees by establishment",
                StructuredArguments.value("payload", establishmentEmployees));

        return ResponseEntity.ok(establishmentEmployees);
    }

    @RequestMapping(value = {"/employee/{employeeId}"}, method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("employeeId") Long employeeId) {

        LOGGER.info("Started - Get employee by employeeId", StructuredArguments.value("employeeId", employeeId));

        EstablishmentEmployee establishmentEmployee = service.get(employeeId);

        LOGGER.info("Ended - Get employee by employeeId", StructuredArguments.value("payload", establishmentEmployee));

        return ResponseEntity.ok(establishmentEmployee);
    }

    @RequestMapping(value = {"/employee"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(EstablishmentEmployee establishmentEmployee, @RequestParam("username") String username) {

        LOGGER.info("Started - Create employee", StructuredArguments.value("payload", establishmentEmployee));

        EstablishmentEmployee responseBody = service.create(establishmentEmployee, username);

        LOGGER.info("Ended - Create employee", StructuredArguments.value("payload", responseBody));

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/employee"}, method = RequestMethod.PUT)
    public ResponseEntity<?> edit(EstablishmentEmployee establishmentEmployee, @RequestParam("username") String username) {

        LOGGER.info("Started - Edit employee", StructuredArguments.value("payload", establishmentEmployee));

        service.edit(establishmentEmployee, username);

        LOGGER.info("Ended - Edit employee");

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = {"/employee/{employeeId}"}, method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@PathVariable("employeeId") Long employeeId, @RequestParam("username") String username) {

        LOGGER.info("Started - Delete employee", StructuredArguments.value("employeeId", employeeId));

        service.delete(employeeId, username);

        LOGGER.info("Ended - Delete employee");

        return ResponseEntity.ok().build();
    }

}
