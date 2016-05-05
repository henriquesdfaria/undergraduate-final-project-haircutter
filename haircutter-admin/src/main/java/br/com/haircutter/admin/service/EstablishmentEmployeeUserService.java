package br.com.haircutter.admin.service;

import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by hfaria on 07/04/16.
 */
@Service
public class EstablishmentEmployeeUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public String getCnpjByLoggedUserUsername() {

        String cnpj = jdbcTemplate.queryForObject(
                "SELECT ee.establishment_cnpj FROM establishment_employee ee WHERE ee.username = ?;",
                new Object[]{LoggedUserUtils.getLoggedUserUsername()},
                String.class);

        return cnpj;
    }

    public Long getEmployeeIdByLoggedUserUsername() {

        Long id = jdbcTemplate.queryForObject(
                "SELECT ee.id FROM establishment_employee ee WHERE ee.username = ?;",
                new Object[]{LoggedUserUtils.getLoggedUserUsername()},
                Long.class);

        return id;
    }


}
