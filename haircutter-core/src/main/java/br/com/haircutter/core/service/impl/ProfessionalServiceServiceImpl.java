package br.com.haircutter.core.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.haircutter.core.model.EstablishmentService;
import br.com.haircutter.core.model.ProfessionalService;
import br.com.haircutter.core.model.repository.EstablishmentServiceRespository;
import br.com.haircutter.core.model.repository.ProfessionalServiceRepository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.ProfessionalServiceService;
import br.com.haircutter.core.validator.ProfessionalServiceValidator;

public class ProfessionalServiceServiceImpl implements ProfessionalServiceService{

	@Autowired
	private ProfessionalServiceRepository professionalServiceRepository;
	
    @Autowired
    private ProfessionalServiceValidator validator;
	
    @Autowired
    private EstablishmentAuditLogService auditLogService;
    
    @Autowired
    private EstablishmentServiceRespository establishmentServiceRespository;
	
	public ProfessionalService create(ProfessionalService professionalService, String cnpj, String username){
		
		validator.validate(professionalService);
		
		EstablishmentService establishmentService = establishmentServiceRespository.findOne(professionalService.getEstablishmentServiceId());
		
		professionalServiceRepository.save(professionalService);
			
		auditLogService.registerLog(cnpj, username, "O profissional agora atende o serviço " + establishmentService.getService());
		
		return professionalService;
	}
	
	public void delete(ProfessionalService professionalService, String cnpj, String username){
		
		EstablishmentService establishmentService = establishmentServiceRespository.findOne(professionalService.getEstablishmentServiceId());
		
		professionalServiceRepository.delete(professionalService);
		
		auditLogService.registerLog(cnpj, username, "O profissional não atende mais o serviço " + establishmentService.getService());
	}

	@Override
	public List<ProfessionalService> findByProfessional(Long establishmentEmployeeId) {
		
		List<ProfessionalService> professionalServiceList = professionalServiceRepository.findAllByEstablishmentEmployeeId(establishmentEmployeeId);
		
		return professionalServiceList;
	}
	
}
