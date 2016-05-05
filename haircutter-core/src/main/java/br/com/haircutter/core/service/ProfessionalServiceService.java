package br.com.haircutter.core.service;

import br.com.haircutter.core.model.ProfessionalService;

import java.util.List;

public interface ProfessionalServiceService {
	
	ProfessionalService create(ProfessionalService professionalService, String cnpj, String username);
	
	void delete(Long professionalServiceId, String cnpj, String username);
	
	List<ProfessionalService> getAllByEstablishmentEmployeeId(Long establishmentEmployeeId);

}
