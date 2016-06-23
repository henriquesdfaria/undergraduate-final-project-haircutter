package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ComplaintEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ComplaintFacade {

	@Autowired
	private ComplaintEndpoint complaintEndpoint;

	@RequestMapping(value = "/moderator/complaints", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok(complaintEndpoint.getAll());
	}

	@RequestMapping(value = "/moderator/complaint/{complaintId}/resolve", method = RequestMethod.PUT)
	public void resolve(@PathVariable("complaintId") Long complaintId) {

		complaintEndpoint.resolve(complaintId);
	}

}
