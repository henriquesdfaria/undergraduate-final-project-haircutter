package br.com.haircutter.admin.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.haircutter.admin.model.User;
import br.com.haircutter.admin.service.UserService;

@RestController
@RequestMapping(value = "/api")
public class UserFacade {

	@Autowired
	private UserService service;

	@RequestMapping(value = "/public/get-logged-user", method = RequestMethod.GET)
	public ResponseEntity<?> getLoggedUser() {

		User userJson = service.getLoggedUser();

		return ResponseEntity.ok(userJson);

	}

}
