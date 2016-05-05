package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.model.EstablishmentService;
import br.com.haircutter.core.model.ProfessionalService;
import br.com.haircutter.core.model.repository.EstablishmentServiceRespository;
import br.com.haircutter.core.model.repository.ProfessionalServiceRepository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.EstablishmentEmployeeService;
import br.com.haircutter.core.service.ProfessionalServiceService;
import br.com.haircutter.core.validator.ProfessionalServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProfessionalServiceServiceImpl implements ProfessionalServiceService {

    @Autowired
    private ProfessionalServiceRepository professionalServiceRepository;

    @Autowired
    private EstablishmentEmployeeService establishmentEmployeeService;

    @Autowired
    private ProfessionalServiceValidator validator;

    @Autowired
    private EstablishmentAuditLogService auditLogService;


    @Autowired
    private EstablishmentServiceRespository establishmentServiceRespository;

    public ProfessionalService create(ProfessionalService professionalService, String cnpj, String username) {

        validator.validate(professionalService);

        EstablishmentService establishmentService = establishmentServiceRespository.findOneByIdAndEstablishmentCnpjAndDeleted(professionalService.getEstablishmentServiceId(), cnpj, false);

        professionalService.setEstablishmentServiceId(establishmentService.getId());

        professionalServiceRepository.save(professionalService);

        auditLogService.registerLog(cnpj, username, "O profissional agora atende o serviço " + establishmentService.getService());

        return professionalService;
    }

    public void delete(Long professionalServiceId, String cnpj, String username) {

        ProfessionalService professionalService = professionalServiceRepository.findOne(professionalServiceId);

        EstablishmentService establishmentService = establishmentServiceRespository.findOneByIdAndEstablishmentCnpjAndDeleted(professionalService.getEstablishmentServiceId(), cnpj, false);

        professionalServiceRepository.delete(professionalService.getId());

        auditLogService.registerLog(cnpj, username, "O profissional não atende mais o serviço " + establishmentService.getService());
    }

    @Override
    public List<ProfessionalService> getAllByEstablishmentEmployeeId(Long establishmentEmployeeId) {

        List<ProfessionalService> professionalServices = professionalServiceRepository.findAllByEstablishmentEmployeeId(establishmentEmployeeId);

        professionalServices.stream().forEach(ps -> {
            ps.setEstablishmentEmployee(establishmentEmployeeService.get(ps.getEstablishmentEmployeeId()));
            ps.setEstablishmentService(establishmentServiceRespository.findOne(ps.getEstablishmentServiceId()));
        });

        return professionalServices;
    }

}
