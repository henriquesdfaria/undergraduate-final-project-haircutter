package br.com.haircutter.core.validator;

import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.UserRole;
import org.springframework.stereotype.Component;

/**
 * Created by hfaria on 31/03/16.
 */
@Component
public class UserRoleValidator {

    public void validate(final UserRole userRole) {

        if (userRole == null) {
            throw new CustomInvalidException("Should not be null", "userRole");
        }

        if (userRole.getUsername() == null) {
            throw new CustomInvalidException("Should not be null", "userRole.username");
        }

        if (userRole.getRole() == null) {
            throw new CustomInvalidException("Should not be null", "userRole.role");
        }
    }
}
