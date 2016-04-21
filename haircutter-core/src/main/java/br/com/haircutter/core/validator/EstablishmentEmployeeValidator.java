package br.com.haircutter.core.validator;

import br.com.haircutter.core.enums.UserRoleEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.EstablishmentEmployee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by hfaria on 31/03/16.
 */
@Component
public class EstablishmentEmployeeValidator {

    @Autowired
    UserValidator userValidator;

    public void validateCreate(final EstablishmentEmployee establishmentEmployee) {

        if (establishmentEmployee == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee");
        }

        if (establishmentEmployee.getId() != null) {
            throw new CustomInvalidException("Should be null", "establishmentEmployee.id");
        }

        if (establishmentEmployee.getEstablishmentCnpj() == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee.establishmentCnpj");
        }

        userValidator.validate(establishmentEmployee.getUser());

        if (establishmentEmployee.getUser().getRole() == null) {
            throw new CustomInvalidException("Should not be null", "role");
        }

        if (!(UserRoleEnum.ROLE_ATTENDANT.equals(establishmentEmployee.getUser().getRole())
                || UserRoleEnum.ROLE_MANAGER.equals(establishmentEmployee.getUser().getRole())
                || UserRoleEnum.ROLE_PROFESSIONAL.equals(establishmentEmployee.getUser().getRole()))) {

            throw new CustomInvalidException("Invalid role", "role");
        }

    }

    public void validateEdit(EstablishmentEmployee establishmentEmployee) {

        if (establishmentEmployee == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee");
        }

        if (establishmentEmployee.getId() == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee.id");
        }

        if (establishmentEmployee.getEstablishmentCnpj() == null) {
            throw new CustomInvalidException("Should not be null", "establishmentEmployee.establishmentCnpj");
        }

        userValidator.validate(establishmentEmployee.getUser());

        if (establishmentEmployee.getUser().getRole() == null) {
            throw new CustomInvalidException("Should not be null", "role");
        }

        if (!(UserRoleEnum.ROLE_ATTENDANT.equals(establishmentEmployee.getUser().getRole())
                || UserRoleEnum.ROLE_MANAGER.equals(establishmentEmployee.getUser().getRole())
                || UserRoleEnum.ROLE_PROFESSIONAL.equals(establishmentEmployee.getUser().getRole()))) {

            throw new CustomInvalidException("Invalid role", "role");
        }
    }
}
