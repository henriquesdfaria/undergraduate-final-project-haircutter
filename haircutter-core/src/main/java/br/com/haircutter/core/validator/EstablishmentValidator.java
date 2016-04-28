package br.com.haircutter.core.validator;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.utils.BrazilCNPValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EstablishmentValidator {

	@Autowired
	AddressValidator addressValidator;

	public void validateEdit(final Establishment establishment) {

		if (establishment.getCnpj() == null) {
			throw new CustomInvalidException("The field is required", "cnpj");
		}

		if (!BrazilCNPValidator.isValidCNPJ(establishment.getCnpj())) {
			throw new CustomInvalidException("The CNPJ is invalid", "cnpj");
		}

		if (establishment.getName() == null) {
			throw new CustomInvalidException("The field is required", "name");
		}

		if (establishment.getDescription() == null) {
			throw new CustomInvalidException("The field is required", "description");
		}

		if (establishment.getPhone() == null) {
			throw new CustomInvalidException("The field is required", "phone");
		}

		if (establishment.getStatus() == null) {
			throw new CustomInvalidException("The field is required", "status");
		}

		if (!EstablishmentStatusEnum.ACTIVE.equals(establishment.getStatus())) {
			throw new CustomInvalidException("The status should be ACTIVE", "status");
		}

		if (establishment.getOwnerName() == null) {
			throw new CustomInvalidException("The field is required", "ownerName");
		}

		if (establishment.getOwnerCpf() == null) {
			throw new CustomInvalidException("The field is required", "ownerCpf");
		}

		if (!BrazilCNPValidator.isValidCPF(establishment.getOwnerCpf())) {
			throw new CustomInvalidException("The CPF is invalid", "ownerCpf");
		}

		if (establishment.getOwnerEmail() == null) {
			throw new CustomInvalidException("The field is required", "ownerEmail");
		}

		if (establishment.getOwnerPhone() == null) {
			throw new CustomInvalidException("The field is required", "ownerPhone");
		}

		addressValidator.validate(establishment.getAddress());

	}

	public void validateInactivate(Establishment establishment) {	
		if (establishment == null) {
			throw new CustomInvalidException("Establishment not found", "cnpj");
		}
		
	}
}
