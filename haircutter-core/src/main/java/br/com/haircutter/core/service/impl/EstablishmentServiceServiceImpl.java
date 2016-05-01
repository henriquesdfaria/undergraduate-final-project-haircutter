package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.exception.CustomInvalidException;
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

        return establishmentServiceRespository.findAllByEstablishmentCnpjAndDeleted(cnpj, false);
    }

    @Override
    public void edit(EstablishmentService establishmentService, String username) {

        EstablishmentService foundEstablishmentService = establishmentServiceRespository
                .findOneByIdAndEstablishmentCnpjAndDeleted(establishmentService.getId(), establishmentService.getEstablishmentCnpj(), false);


        if (foundEstablishmentService == null) {
            throw new CustomInvalidException("You should not edit this service, it is from another establishment");
        }

        establishmentServiceValidator.validateEdit(establishmentService);

        establishmentService.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        establishmentService.setDeleted(false);

        EstablishmentService editedEstablishmentService = establishmentServiceRespository.save(establishmentService);

        establishmentAuditLogService.registerLog(editedEstablishmentService.getEstablishmentCnpj(), username,
                "Editou o serviço \"" + establishmentService.getService() + "\"");

    }

    @Override
    public void delete(Long id, String cnpj, String username) {

        EstablishmentService foundEstablishmentService = establishmentServiceRespository
                .findOneByIdAndEstablishmentCnpjAndDeleted(id, cnpj, false);

        if (foundEstablishmentService == null) {
            throw new CustomInvalidException("You should not delete this service, it is from another establishment");
        }

        foundEstablishmentService.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));
        foundEstablishmentService.setDeleted(true);

        EstablishmentService editedEstablishmentService = establishmentServiceRespository.save(foundEstablishmentService);

        establishmentAuditLogService.registerLog(editedEstablishmentService.getEstablishmentCnpj(), username,
                "Deletou o serviço \"" + foundEstablishmentService.getService() + "\"");

    }

    @Override
    public EstablishmentService getByIdAndCnpj(Long id, String cnpj) {
        return establishmentServiceRespository.findOneByIdAndEstablishmentCnpjAndDeleted(id, cnpj, false);
    }
}
