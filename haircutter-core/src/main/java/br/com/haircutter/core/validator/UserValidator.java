package br.com.haircutter.core.validator;

import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by hfaria on 31/03/16.
 */
@Component
public class UserValidator {

    public void validate(final User user) {

        if (user == null) {
            throw new CustomInvalidException("Should not be null", "user");
        }

        if (user.getUsername() == null ) {
            throw new CustomInvalidException("Should not be null", "user.username");
        }

        if (user.getName() == null) {
            throw new CustomInvalidException("Should not be null", "user.name");
        }
    }
}
