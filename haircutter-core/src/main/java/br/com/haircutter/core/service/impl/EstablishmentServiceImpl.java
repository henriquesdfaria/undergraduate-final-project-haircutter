package br.com.haircutter.core.service.impl;

import br.com.haircutter.core.model.Establishment;
import br.com.haircutter.core.model.repository.EstablishmentRespository;
import br.com.haircutter.core.service.EstablishmentAuditLogService;
import br.com.haircutter.core.service.EstablishmentService;
import br.com.haircutter.core.validator.EstablishmentServiceValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;

/**
 * Created by hfaria on 31/03/16.
 */
@Service
public class EstablishmentServiceImpl implements EstablishmentService {

    @Autowired
    EstablishmentRespository establishmentRespository;

    @Autowired
    EstablishmentServiceValidator validator;

    @Autowired
    EstablishmentAuditLogService auditLogService;

    @Override
    public Establishment get(String cnpj) {

        return establishmentRespository.findOneByCnpj(cnpj);
    }

    @Override
    public void edit(Establishment establishment, String username) {

        validator.validateEdit(establishment);

        establishment.setLastModifiedDate(new Date(ZonedDateTime.now().toInstant().toEpochMilli()));

        Establishment editedEstablishment = establishmentRespository.save(establishment);

        auditLogService.registerLog(editedEstablishment.getCnpj(), username, "Editou perfil do estabelecimento");
    }
}
