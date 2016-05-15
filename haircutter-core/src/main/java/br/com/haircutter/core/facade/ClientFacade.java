package br.com.haircutter.core.facade;

import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.model.User;
import br.com.haircutter.core.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/client")
public class ClientFacade {

    Logger LOGGER = LoggerFactory.getLogger(ClientFacade.class);

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody User user) {

        LOGGER.info("Started - Register new User", user);

        User createdUser = userService.create(user, UserRoleEnum.ROLE_CLIENT);

        LOGGER.info("Ended - Register new User", createdUser);

        return ResponseEntity.ok(createdUser);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public void edit(@RequestBody User user) {

        LOGGER.info("Started - Edit User", user);

        userService.edit(user);

        LOGGER.info("Ended - Edit User");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("username") String username) {

        LOGGER.info("Started - Inactivate User", username);

        userService.delete(username);

        LOGGER.info("Ended - Inactivate User");
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam("username") String username) {

        LOGGER.info("Started - Get User", username);

        User user = userService.get(username);

        LOGGER.info("Ended - Get User", user);

        return ResponseEntity.ok(user);
    }

}
