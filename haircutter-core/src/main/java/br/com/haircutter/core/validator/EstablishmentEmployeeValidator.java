package br.com.haircutter.core.validator;

import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.EstablishmentEmployee;
import br.com.haircutter.core.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hfaria on 31/03/16.
 */
@Component
public class EstablishmentEmployeeValidator {

    @Autowired
    UserValidator userValidator;

    @Autowired
    UserRoleValidator userRoleValidator;

    public void validateCreate(final EstablishmentEmployee establishmentEmployee) {

        if (establishmentEmployee == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee");
        }

        if (establishmentEmployee.getId() != null) {
            throw new CustomInvalidException("Should be null", "establishmentEmployee.id");
        }

        if (establishmentEmployee.getEstablishmentCnpj() != null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee.establishmentCnpj");
        }

        userValidator.validate(establishmentEmployee.getUser());

        UserRole userRole = establishmentEmployee.getUser().getUserRoles().get(0);

        userRoleValidator.validate(userRole);

        if (!(UserRoleEnum.ROLE_ATTENDANT.equals(userRole.getRole())
                || UserRoleEnum.ROLE_MANAGER.equals(userRole.getRole())
                || UserRoleEnum.ROLE_PROFFESSIONAL.equals(userRole.getRole()))) {

            throw new CustomInvalidException("Invalid role", "userRole.role");
        }

    }

    public void validateEdit(EstablishmentEmployee establishmentEmployee) {

        if (establishmentEmployee == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee");
        }

        if (establishmentEmployee.getId() == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee.id");
        }

        if (establishmentEmployee.getEstablishmentCnpj() != null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee.establishmentCnpj");
        }

        userValidator.validate(establishmentEmployee.getUser());

        UserRole userRole = establishmentEmployee.getUser().getUserRoles().get(0);

        userRoleValidator.validate(userRole);

        if (!(UserRoleEnum.ROLE_ATTENDANT.equals(userRole.getRole())
                || UserRoleEnum.ROLE_MANAGER.equals(userRole.getRole())
                || UserRoleEnum.ROLE_PROFFESSIONAL.equals(userRole.getRole()))) {

            throw new CustomInvalidException("Invalid role", "userRole.role");
        }
    }
}
