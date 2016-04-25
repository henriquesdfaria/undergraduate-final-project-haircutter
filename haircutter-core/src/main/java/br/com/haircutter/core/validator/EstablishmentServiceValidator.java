package br.com.haircutter.core.validator;

import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.EstablishmentService;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentServiceValidator {

	public void validateEdit(final EstablishmentService establishmentService) {

		if (establishmentService.getId() == null) {
			throw new CustomInvalidException("Should not be null", "id");
		}

	}

    public void validateCreate(EstablishmentService establishmentService) {

        if (establishmentService.getId() != null) {
            throw new CustomInvalidException("Should be null", "id");
        }

    }
}
