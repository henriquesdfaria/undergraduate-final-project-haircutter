package br.com.haircutter.core.validator;

import br.com.haircutter.core.exception.CustomInvalidException;
import br.com.haircutter.core.model.ProfessionalCalendar;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalCalendarValidator {
	
	public void validate(final ProfessionalCalendar professionalCalendar){
		if(professionalCalendar.getEstablishmentEmployeeId() != null){
			throw new CustomInvalidException("Should be null", "EstablishmentEmployeeId");
		}
	}
}
