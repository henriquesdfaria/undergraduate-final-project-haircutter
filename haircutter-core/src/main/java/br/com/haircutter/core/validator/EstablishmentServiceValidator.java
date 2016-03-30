package br.com.haircutter.core.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.haircutter.core.enums.EstablishmentStatusEnum;
import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.Address;
import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.utils.BrazilCNPValidator;

@Component
public class EstablishmentServiceValidator {
	
	@Autowired
	EstablishmentRespository establishmentRepository;

	public void validateNewCreationRequest(final Establishment establishment) {

		if (establishment.getCnpj() == null) {
			throw new CustomInvalidException("The field is required", "cnpj");
		}
		
		if (!BrazilCNPValidator.isValidCNPJ(establishment.getCnpj())) {
			throw new CustomInvalidException("The CNPJ is invalid", "cnpj");
		}
		
		if (establishmentRepository.exists(establishment.getCnpj())) {
			throw new CustomInvalidException("This establishment has been registered", "cnpj");
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

		if (establishment.getStatus() != null) {
			throw new CustomInvalidException("The field is not required", "status");
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

		if (establishment.getCreationTime() != null) {
			throw new CustomInvalidException("The field is not required", "creationTime");
		}

		if (establishment.getLastModifiedDate() != null) {
			throw new CustomInvalidException("The field is not required", "lastModifiedDate");
		}
		
		if (establishment.getLastModifiedDate() != null) {
			throw new CustomInvalidException("The field is not required", "lastModifiedDate");
		}
		
		validateAddress(establishment.getAddress());

	}

	public void validateApproveOrDenyCreationRequest(final Establishment establishment) {

		if (establishment == null) {
			throw new CustomInvalidException("The request is not found", "cnpj");
		}
		
		if (EstablishmentStatusEnum.ACTIVE.equals(establishment.getStatus())
				|| EstablishmentStatusEnum.DENIED.equals(establishment.getStatus())
				|| EstablishmentStatusEnum.INACTIVE.equals(establishment.getStatus())) {
			throw new CustomInvalidException("The status must be WAITING", "status");
		}

	}

	private void validateAddress(final Address address) {
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
