package br.com.haircutter.core.validator;

import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.Address;
import org.springframework.stereotype.Component;

/**
 * Created by hfaria on 31/03/16.
 */
@Component
public class AddressValidator {

    public void validate(final Address address) {
        if (address.getStreet() == null) {
            throw new CustomInvalidException("The field is required", "street");
        }

        if (address.getNeighborhood() == null) {
            throw new CustomInvalidException("The field is required", "neighborhood");
        }

        if (address.getCity() == null) {
            throw new CustomInvalidException("The field is required", "city");
        }

        if (address.getState() == null) {
            throw new CustomInvalidException("The field is required", "state");
        }

        if (address.getCountry() == null) {
            throw new CustomInvalidException("The field is required", "country");
        }

        if (address.getZipCode() == null) {
            throw new CustomInvalidException("The field is required", "zipCode");
        }
    }
}
