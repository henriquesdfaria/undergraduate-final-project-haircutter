package br.com.haircutter.admin.service;

import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by hfaria on 07/04/16.
 */
@Service
public class EstablishmentUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getCnpjByLoggedUserUsername() {

        String cnpj = jdbcTemplate.queryForObject(
                "SELECT ea.establishment_cnpj FROM establishment_admin ea WHERE ea.username = ?;",
                new Object[]{LoggedUserUtils.getLoggedUserUsername()},
                String.class);

        return cnpj;
    }
}
