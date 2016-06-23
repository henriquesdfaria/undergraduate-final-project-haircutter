package br.com.haircutter.core.facade;

import br.com.haircutter.core.model.Complaint;
import br.com.haircutter.core.service.ComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ComplaintFacade {

    Logger LOGGER = LoggerFactory.getLogger(ComplaintFacade.class);

    @Autowired
    ComplaintService complaintService;

    @RequestMapping( value = "/complaints", method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {

        LOGGER.info("Started - Get all complaints with status PENDING");

        List<Complaint> complaints = complaintService.getAllByStatus("PENDING");

        LOGGER.info("Ended - Get all complaints with status PENDING", complaints);

        return ResponseEntity.ok(complaints);
    }

    @RequestMapping( value = "/complaint/{complaintId}", method = RequestMethod.PUT)
    public void resolve(@PathVariable("complaintId") Long complaintId) {

        LOGGER.info("Started - resolve complaint");

        complaintService.resolve(complaintId);

    }

    @RequestMapping( value = "/complaint", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Complaint complaint) {

        LOGGER.info("Started - create complaint");

        Complaint createdComplaint = complaintService.create(complaint);

        LOGGER.info("Ended - create complaint", createdComplaint);

        return ResponseEntity.ok(createdComplaint);
    }
}
