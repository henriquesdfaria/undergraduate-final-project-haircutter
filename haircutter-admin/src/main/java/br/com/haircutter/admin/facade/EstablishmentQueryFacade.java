package br.com.haircutter.admin.facade;

import br.com.haircutter.admin.endpoint.EstablishmentQueryEndpoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hfaria on 4/24/16.
 */
@RestController
@RequestMapping(value = "/api")
public class EstablishmentQueryFacade {

    @Autowired
    EstablishmentQueryEndpoint establishmentQueryEndpoint;

    @RequestMapping(value = {"/public/establishments-query/search"}, method = RequestMethod.GET)
    public ResponseEntity<?> search(@RequestParam("city") String city) {
        return ResponseEntity.ok(establishmentQueryEndpoint.findAllByCity(city));
    }

}
