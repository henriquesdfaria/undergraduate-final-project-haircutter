package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ComplaintEndpoint;
import br.com.haircutter.admin.facade.json.ComplaintJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

	@RequestMapping(value = "/client/complaint", method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody ComplaintJson complaintJson) {

		return ResponseEntity.ok(complaintEndpoint.create(complaintJson));
	}

}
