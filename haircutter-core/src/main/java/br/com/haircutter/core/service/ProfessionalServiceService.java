package br.com.haircutter.core.service;

import java.util.List;

import br.com.haircutter.core.model.ProfessionalService;

public interface ProfessionalServiceService {
	
	ProfessionalService create(ProfessionalService professionalService, String cnpj, String username);
	
	void delete(ProfessionalService professionalService, String cnpj, String username);
	
	List<ProfessionalService> findByProfessional(Long establishmentEmployeeId);
}
