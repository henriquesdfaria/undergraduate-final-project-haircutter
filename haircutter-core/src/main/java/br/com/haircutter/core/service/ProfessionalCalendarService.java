package br.com.haircutter.core.service;

import br.com.haircutter.core.model.ProfessionalCalendar;

import java.util.List;

public interface ProfessionalCalendarService {
	
	ProfessionalCalendar create(ProfessionalCalendar professionalCalendar, String cnpj, String username);
	
	void delete(Long professionalCalendarId, String cnpj, String username);
	
	List<ProfessionalCalendar> getAllByEstablishmentEmployeeId(Long establishmentEmployeeId);

}
