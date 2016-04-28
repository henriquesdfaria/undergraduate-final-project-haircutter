package br.com.haircutter.admin.service;

import br.com.haircutter.admin.utils.LoggedUserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by hfaria on 07/04/16.
 */
@Service
public class EstablishmentAdminUserService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Boolean verifyEmployeeIsInOwnEstablishment(Long employeeId) {

        String cnpj = jdbcTemplate.queryForObject(
                "SELECT ea.establishment_cnpj FROM establishment_admin ea WHERE ea.username = ?;",
                new Object[]{LoggedUserUtils.getLoggedUserUsername()},
                String.class);

        String foundEmployeeId = jdbcTemplate.queryForObject(
                "SELECT ee.id FROM establishment_employee ee WHERE ee.establishment_cnpj = ? AND ee.id = ?",
                new Object[]{cnpj, employeeId},
                String.class);

        if (foundEmployeeId == null || foundEmployeeId == "") {
            return false;
        }

        return true;
    }

    public String getCnpjByLoggedUserUsername() {

        String cnpj = jdbcTemplate.queryForObject(
                "SELECT ea.establishment_cnpj FROM establishment_admin ea WHERE ea.username = ?;",
                new Object[]{LoggedUserUtils.getLoggedUserUsername()},
                String.class);

        return cnpj;
    }
}
