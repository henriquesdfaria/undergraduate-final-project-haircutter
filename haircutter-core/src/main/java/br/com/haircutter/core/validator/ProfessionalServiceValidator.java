package br.com.haircutter.core.validator;

import org.springframework.stereotype.Component;

import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.ProfessionalService;

@Component
public class ProfessionalServiceValidator {
	
	public void validate(final ProfessionalService professionalService){
		if(professionalService.getEstablishmentEmployeeId() == null){
			throw new CustomInvalidException("The field is required", "EstablishmentEmployeeId");
		}		
		if(professionalService.getEstablishmentServiceId() == null){
			throw new CustomInvalidException("The field is required", "EstablishmentServiceId");
		}
	}
}
