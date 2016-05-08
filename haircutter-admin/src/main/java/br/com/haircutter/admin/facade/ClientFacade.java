package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.ClientEndpoint;
import br.com.haircutter.admin.facade.json.UserJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ClientFacade {

	@Autowired
	private ClientEndpoint clientEndpoint;

	@RequestMapping(value = "/public/register", method = RequestMethod.POST)
	public ResponseEntity<?> register(@RequestBody UserJson userJson) {
		return ResponseEntity.ok(clientEndpoint.create(userJson));
	}

	@RequestMapping(value = "/client/profile", method = RequestMethod.GET)
	public ResponseEntity<?> getProfile(@RequestBody UserJson userJson) {
		return ResponseEntity.ok(clientEndpoint.get());
	}

	@RequestMapping(value = "/client/profile", method = RequestMethod.PUT)
	public void editProfile(@RequestBody UserJson userJson) {
		clientEndpoint.edit(userJson);
	}

}
