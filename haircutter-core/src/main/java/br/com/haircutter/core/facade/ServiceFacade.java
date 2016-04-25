package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.Service;
import br.com.haircutter.core.service.ServiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping(value = "/api/establishment")
public class ServiceFacade {

    Logger LOGGER = LoggerFactory.getLogger(ServiceFacade.class);

    @Autowired
    ServiceService serviceService;

    @RequestMapping(value = {"/service"}, method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Service service, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Create Service For Establishment", service);

        Service createdService = serviceService.create(service);

        LOGGER.info("Ended by " + username + " - Create Service For Establishment", createdService);

        return ResponseEntity.ok(createdService);
    }

    @RequestMapping(value = {"/service"}, method = RequestMethod.PUT)
    public void edit(@RequestBody Service service, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Edit Establishment Service", service);

        serviceService.edit(service);

        LOGGER.info("Ended by " + username + " - Edit Establishment Service");

    }

    @RequestMapping(value = {"/{cnpj}/service/{id}"}, method = RequestMethod.DELETE)
    public void delete(@PathVariable("cnpj") String cnpj, @PathVariable("id") Long id, @RequestParam("username") String username) {

        LOGGER.info("Started by " + username + " - Edit Establishment Service", id);

        serviceService.delete(id, cnpj, username);

        LOGGER.info("Ended by " + username + " - Edit Establishment Service", id);

    }

    @RequestMapping(value = {"/{cnpj}/service/{id}"}, method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable("cnpj") String cnpj,@PathVariable("id") Long id) {

        LOGGER.info("Started - Get Establishment Service by ID", id);

        Service service = serviceService.getByIdAndCnpj(id, cnpj);

        LOGGER.info("Ended - Get Establishment Service by ID", service);

        return ResponseEntity.ok(service);
    }

    @RequestMapping(value = {"/{cnpj}/services"}, method = RequestMethod.GET)
    public ResponseEntity<?> getAllByCnpj(@PathVariable("cnpj") String cnpj) {

        LOGGER.info("Started - Get Establishment Services by CNPJ", id);

        List<Service> services = serviceService.getAllByCnpj(cnpj);

        LOGGER.info("Ended - Get Establishment Services by CNPJ", services);

        return ResponseEntity.ok(services);
    }


}
