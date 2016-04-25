package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.model.EstablishmentService;
import br.com.haircutter.core.model.repository.EstablishmentServiceRespository;
import br.com.haircutter.core.service.EstablishmentServiceService;
import br.com.haircutter.core.validator.EstablishmentServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

/**
 * Created by hfaria on 31/03/16.
 */
@Service
public class EstablishmentServiceServiceImpl implements EstablishmentServiceService {

    @Autowired
    EstablishmentServiceRespository establishmentServiceRespository;

    @Autowired
    EstablishmentServiceValidator establishmentServiceValidator;

    @Autowired
    EstablishmentAuditLogServiceImpl establishmentAuditLogService;

    @Override
    public EstablishmentService create(EstablishmentService establishmentService, String username) {

        establishmentServiceValidator.validateCreate(establishmentService);

        Date now = new Date(ZonedDateTime.now().toInstant().toEpochMilli());

        establishmentService.setCreationTime(now);
        establishmentService.setLastModifiedDate(now);
        establishmentService.setDeleted(false);

        EstablishmentService createdEstablishmentService = establishmentServiceRespository.save(establishmentService);

        establishmentAuditLogService.registerLog(createdEstablishmentService.getEstablishmentCnpj(), username,
                "Criou serviço para o estabelecimento");

        return createdEstablishmentService;
    }

    @Override
    public List<EstablishmentService> getAllByCnpj(String cnpj) {

        return establishmentServiceRespository.findAllByEstablishmentCnpj(cnpj);
    }

    @Override
    public void edit(EstablishmentService establishmentService, String username) {

        establishmentServiceValidator.validateEdit(establishmentService);

        establishmentService.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        establishmentService.setDeleted(false);

        EstablishmentService editedEstablishmentService = establishmentServiceRespository.save(establishmentService);

        establishmentAuditLogService.registerLog(editedEstablishmentService.getEstablishmentCnpj(), username,
                "Editou o serviço \"" + establishmentService.getService() + "\"");

    }

    @Override
    public void delete(Long id, String cnpj, String username) {

        EstablishmentService establishmentService = establishmentServiceRespository
                .findOneByIdAndEstablishmentCnpj(id, cnpj);

        establishmentService.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        establishmentService.setDeleted(true);

        EstablishmentService editedEstablishmentService = establishmentServiceRespository.save(establishmentService);

        establishmentAuditLogService.registerLog(editedEstablishmentService.getEstablishmentCnpj(), username,
                "Deletou o serviço \"" + establishmentService.getService() + "\"");

    }

    @Override
    public EstablishmentService getByIdAndCnpj(Long id, String cnpj) {
        return establishmentServiceRespository.findOneByIdAndEstablishmentCnpj(id, cnpj);
    }
}
